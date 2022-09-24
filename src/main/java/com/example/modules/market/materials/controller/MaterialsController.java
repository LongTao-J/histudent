package com.example.modules.market.materials.controller;

import com.example.modules.market.materials.mapper.MaterialsMapper;
import com.example.modules.market.materials.pojo.Materials;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/materials")
public class MaterialsController {

    @Autowired
    MaterialsMapper mapper;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    UserService userServiceImpl;

    // 按标签查找资料
    @GetMapping("/get/{tag}")
    @CrossOrigin
    public R<List<Materials>> getByTag(@PathVariable("tag") String tag) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("tag", tag);
        List<Materials> list = mapper.selectByMap(columnMap);
        if(list != null) {
            return R.success(list);
        } else {
            return R.error();
        }
    }

    // 按name(资料名称)模糊查找资料
    @GetMapping("/like/{name}")
    @CrossOrigin
    public R<List<Materials>> getByNameLikeList(@PathVariable("name") String name) {
        addSeachRecord(name);
        List<Materials> list = mapper.queryByNameLikeList(name);
        if(list != null) {
            return R.success(list);
        } else {
            return R.error();
        }
    }

    // 按id查找资料
    @GetMapping("/getId/{id}")
    @CrossOrigin
    public R<Materials> getById(@PathVariable("id") String id) {
        Materials materials = mapper.selectById(id);
        if(materials != null) {
            return R.success(materials);
        } else {
            return R.error();
        }
    }

    // 添加资料
    @PostMapping("/add")
    @CrossOrigin
    public R<Materials> addMaterial(@RequestBody Materials material) {
        ValueOperations<Object,Object> redis = redisTemplate.opsForValue();
        String userId = (String) redis.get(Consts.REDIS_USER);
        material.setUserId(userId);
        material.setCreateTime(new Date());
        int code = mapper.insert(material);
        if(code == 1) {
            return R.success(material);
        } else {
            return R.error();
        }
    }

    // 更新资料
    @PutMapping("/update")
    @CrossOrigin
    public R<Materials> updateMaterial(@RequestBody Materials material) {
        material.setCreateTime(new Date());
        int code = mapper.updateById(material);
        if(code == 1) {
            return R.success(material);
        } else {
            return R.error();
        }
    }

    // 删除资料
    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public R<String> deleteMaterial(@PathVariable("id") String id) {
        int code = mapper.deleteById(id);
        if(code == 1) {
            return R.success(id);
        } else {
            return R.error();
        }
    }

    private void addSeachRecord(String content){
        // redis获取当前用户id
        String userId = userServiceImpl.getTokenUser().getId();
        // 防止用户输入数据中存在拆分符号，因此提高拆分符号的复杂度
        Boolean flag = redisTemplate.opsForHash().hasKey("MATERIAL_SEARCH_RECORD", userId + "::" + content);
        if(flag){
            Integer searchRecord = (Integer)redisTemplate.opsForHash().get("MATERIAL_SEARCH_RECORD", userId + "::" + content);
            redisTemplate.opsForHash().put("MATERIAL_SEARCH_RECORD", userId + "::" + content, searchRecord + 1);
        }else{
            redisTemplate.opsForHash().put("MATERIAL_SEARCH_RECORD", userId + "::" + content, 1);
        }
    }

    @GetMapping("/get-hot-words")
    @CrossOrigin
    public R<Object> getHotWords(){
        Map<String, Integer> map = new HashMap<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("MATERIAL_SEARCH_RECORD", ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 userId，content
            String[] split = key.split("::");
            Integer num = map.get(split[1]);
            if(num == null) {
                map.put(
                        split[1],
                        (Integer)redisTemplate.opsForHash().get("MATERIAL_SEARCH_RECORD", key)
                );
            }
            else{
                map.put(
                        split[1],
                        (Integer)redisTemplate.opsForHash().get("MATERIAL_SEARCH_RECORD", key) + map.get(split[1])
                );
            }
        }
        cursor.close();
        return R.success(map);
    }


}

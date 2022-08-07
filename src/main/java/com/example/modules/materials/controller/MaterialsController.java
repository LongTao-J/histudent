package com.example.modules.materials.controller;

import com.example.modules.materials.mapper.MaterialsMapper;
import com.example.modules.materials.pojo.Materials;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/materials")
public class MaterialsController {

    @Autowired
    MaterialsMapper mapper;

    // 按标签查找资料
    @GetMapping("/get/{tag}")
    public R<List<Materials>> getByTag(@PathVariable("tag") String tag) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("tag", tag);
        List<Materials> list = mapper.selectByMap(columnMap);
        if(list != null)
            return R.success(list);
        else
            return R.error();
    }

    // 按name(资料名称)模糊查找资料
    @GetMapping("/like/{name}")
    public R<List<Materials>> getByNameLikeList(@PathVariable("name") String name) {
        List<Materials> list = mapper.queryByNameLikeList(name);
        if(list != null)
            return R.success(list);
        else
            return R.error();
    }

    // 添加资料
    @PostMapping("/add")
    public R<Materials> addMaterial(@RequestBody Materials material) {
        material.setCreateTime(new Date());
        int code = mapper.insert(material);
        if(code == 1)
            return R.success(material);
        else
            return R.error();
    }

    // 更新资料
    @PutMapping("/update")
    public R<Materials> updateMaterial(@RequestBody Materials material) {
        material.setCreateTime(new Date());
        int code = mapper.updateById(material);
        if(code == 1)
            return R.success(material);
        else
            return R.error();
    }

    // 删除资料
    @DeleteMapping("/delete/{id}")
    public R<String> deleteMaterial(@PathVariable("id") String id) {
        int code = mapper.deleteById(id);
        if(code == 1)
            return R.success(id);
        else
            return R.error();
    }


}
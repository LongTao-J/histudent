package com.example.modules.file.controller;

import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.pojo.FileUploadResponse;
import com.example.modules.user.pojo.User;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import com.example.utils.minioUtils.MinioUtil;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/file")
public class MinioController {

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @CrossOrigin
//    public FileUploadResponse upload(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam(required = false, defaultValue = "test") String bucketName) {
      public R<FileUploadResponse> upload(@RequestParam(name = "file", required = false) MultipartFile file) {
        FileUploadResponse response = null;
        try {
            response = minioUtil.uploadFile(file, "test");
        } catch (Exception e) {
            log.error("上传失败 : [{}]", Arrays.asList(e.getStackTrace()));
        }

        return R.success(response,"图片上传成功",200);
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/delete/{objectName}")
    @CrossOrigin
    public void delete(@PathVariable("objectName") String objectName, @RequestParam(required = false, defaultValue = "test") String bucketName) throws Exception {
        minioUtil.removeObject(bucketName, objectName);
        log.error("删除成功");
    }

    /**
     * 下载文件到本地
     */
    @GetMapping("/download/{objectName}")
    @CrossOrigin
    public ResponseEntity<byte[]> downloadToLocal(@PathVariable("objectName") String objectName, HttpServletResponse response) throws Exception {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream stream = null;
        ByteArrayOutputStream output = null;
        try {
            // 获取"myobject"的输入流。
            stream = minioUtil.getObject("test", objectName);
            if (stream == null) {
                log.error("文件不存在");
                throw new RuntimeException("文件不存在");
            }
            //用于转换byte
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();

            //设置header
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept-Ranges", "bytes");
            httpHeaders.add("Content-Length", bytes.length + "");
//            objectName = new String(objectName.getBytes("UTF-8"), "ISO8859-1");
            //把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
            httpHeaders.add("Content-disposition", "attachment; filename=" + objectName);
            httpHeaders.add("Content-Type", "text/plain;charset=utf-8");
//            httpHeaders.add("Content-Type", "image/jpeg");
            responseEntity = new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);

        } catch (MinioException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (output != null) {
                output.close();
            }
        }
        return responseEntity;
    }

    /**
     * 在浏览器预览图片
     */
    @GetMapping("/preViewPicture/{objectName}")
    @CrossOrigin
    public void preViewPicture(@PathVariable("objectName") String objectName, HttpServletResponse response) throws Exception {
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            InputStream stream = minioUtil.getObject("test", objectName);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();
            out.write(bytes);
            out.flush();
        }
    }

    @GetMapping("/getBucket")
    @CrossOrigin
    public String getBucket() {
        List<Bucket> bucketList = null;
//        ArrayList<String> str = null;
        StringBuilder json = new StringBuilder("[");
        try {
            bucketList =  minioUtil.getAllBuckets();
            for(Bucket b: bucketList){
                System.out.println(b.name());
                json.append("{").append(b.name()).append("},");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        json.deleteCharAt(json.length()-1);
        json.append("]");
        return json.toString();
    }




}

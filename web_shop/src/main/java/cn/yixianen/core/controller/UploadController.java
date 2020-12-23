package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yixianen
 * @Date 2020/12/8 14:08
 * @version 1.0
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    @RequestMapping("/uploadFile")
    public Result uploadFile(MultipartFile file){
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:/fastDfs/fdfs_client.conf");
            String path = fastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename(), file.getSize());
            return new Result(true, FILE_SERVER_URL+path);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "上传文件失败");
        }
    }
}

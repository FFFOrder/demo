package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 保存商品图片，命名为 商品编号.jpg ，会覆盖已有的图片
 */
@Component
public class SaveImage {
    @Value("${web.filepath}")
    private String path;
    public void save(String id, MultipartFile image){
        String filename = id + ".jpg";
        try {
            File file = new File(path + File.separator + "images" + File.separator + filename);
            if (!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream = (FileInputStream) image.getInputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buffer = new byte[10240];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1){
                bufferedOutputStream.write(buffer, 0, len);
            }
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

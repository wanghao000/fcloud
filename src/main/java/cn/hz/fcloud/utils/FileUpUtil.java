package cn.hz.fcloud.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.UUID;

public class FileUpUtil {

    /***
     * 文件上传
     * @param file 需要上传的文件
     * @return 上传成功后返回的路径
     */
    public static String fileUp(MultipartFile file){
        String picture = "";
        String name = null;
        Boolean exist_file = file != null;
        if(exist_file){
            String originalFilename = file.getOriginalFilename();
            try{
                Resource r = new ClassPathResource("/file.properties");
                Properties prop = new Properties();
                prop.load(new FileInputStream(r.getFile()));
                String realPath = prop.getProperty("dir");
                name = "/"+ UUID.randomUUID()+"."+originalFilename.split("\\.")[1];
                picture = realPath+name;
                File files = new File(realPath);
                if(!files.exists()){
                    files.mkdirs();
                }
                File saveFile = new File(picture);
                file.transferTo(saveFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return name;
    }
}



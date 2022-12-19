package br.com.newgo.spring.shopng.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

@Component
public class Disk {

    @Value("${user.disk.image-directory}")
    private String imagesDirectory;

    public String saveImage(MultipartFile image){
        return this.save(this.imagesDirectory, image);
    }

    public String save(String imagesDirectory, MultipartFile file){
        Path directoryPath = Paths.get(imagesDirectory);
        Path filePath = directoryPath.resolve(file.getOriginalFilename());

        try{
            file.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Error: image not saved.",e);
        }
        return filePath.toString();
    }
    //TODO validação para nome de imagem já existente
}

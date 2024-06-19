package vn.com.ltdt.Coffee_Shop.images;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    @Value("${project.images}")
    String path;

    @Override
    public List<String> uploadImage(List<MultipartFile> files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        files.forEach(file -> {
            String name = file.getOriginalFilename();
            String randomID = UUID.randomUUID().toString();
            String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

            String fullPath = path + File.separator + Paths.get(fileName);

            File f = new File(path);
            if(!f.exists()){
                f.mkdir();
            }
            try {
                Files.copy(file.getInputStream(), Paths.get(fullPath));
                fileNames.add(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return fileNames;
    }

    @Override
    public InputStream getResource(String name) throws FileNotFoundException {
        return new FileInputStream(path+name);
    }
}

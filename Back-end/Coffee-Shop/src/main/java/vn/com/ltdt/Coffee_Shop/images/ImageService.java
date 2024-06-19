package vn.com.ltdt.Coffee_Shop.images;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ImageService {
    InputStream getResource(String name) throws FileNotFoundException;
    List<String> uploadImage(List<MultipartFile> file) throws IOException;
}

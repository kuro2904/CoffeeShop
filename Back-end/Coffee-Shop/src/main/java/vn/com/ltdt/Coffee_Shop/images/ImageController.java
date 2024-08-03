package vn.com.ltdt.Coffee_Shop.images;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("{name}")
    public void getResource(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(imageService.getResource(name), response.getOutputStream());
    }

    @PostMapping
    public ResponseEntity<List<String>> uploadImage(@RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(imageService.uploadImage(files));
    }

}

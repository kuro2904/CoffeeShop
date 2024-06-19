package vn.com.ltdt.Coffee_Shop.utils.mappers;

import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.images.Image;
import vn.com.ltdt.Coffee_Shop.images.ImageDTO;

@Service
public class ImageMapper {

    public ImageDTO mapToDTO(Image image) {
        return new ImageDTO(image.getId(), image.getUrl());
    }

}

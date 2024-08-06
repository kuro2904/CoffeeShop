package vn.com.ltdt.Coffee_Shop.utils.mappers;

import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.rating.Rating;
import vn.com.ltdt.Coffee_Shop.rating.RatingDTO;

@Service
public class RatingMapper {

    public RatingDTO mapToDTO(Rating rating) {
        return new RatingDTO(
                rating.getId(),
                rating.getProduct().getId(),
                rating.getRating()
        );
    }

}

package vn.com.ltdt.Coffee_Shop.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.exceptions.ResourceNotFound;
import vn.com.ltdt.Coffee_Shop.product.Product;
import vn.com.ltdt.Coffee_Shop.product.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;

    public void addRating(RatingDTO req) {
        Product product = productRepository.findById(req.productId()).orElseThrow(
                () -> new ResourceNotFound("Product not found","Id",String.valueOf(req.productId()))
        );
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setRating(req.ratingScore());
        ratingRepository.save(rating);
    }

    public void deleteRating(int req) {
        Rating rating = ratingRepository.findById(req).orElseThrow(
                () -> new ResourceNotFound("Rating not found","Id",String.valueOf(req))
        );
        ratingRepository.delete(rating);
    }

    public void editRating(int id, double ratingScore) {
        Rating rating = ratingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Rating not found","Id",String.valueOf(id))
        );
        rating.setRating(ratingScore);
        ratingRepository.save(rating);
    }

}

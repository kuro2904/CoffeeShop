package vn.com.ltdt.Coffee_Shop.rating;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Void> addRating(@Valid @RequestBody RatingDTO rating) {
        ratingService.addRating(rating);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateRating(@RequestParam(name = "id") int id, @RequestParam(name = "ratingScore") double ratingScore) {
        ratingService.editRating(id, ratingScore);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRating(@RequestParam(name = "id") int id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

}

package vn.com.ltdt.Coffee_Shop.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RatingDTO(
        int id,
        @NotNull(message = "ProductId not valid")
        int productId,
        @NotNull(message = "Rating score not valid")
        @Max(value = 5, message = "Value maximum is 5")
        @Min(value = 0, message = "Value minimum is 0")
        double ratingScore
) {
}

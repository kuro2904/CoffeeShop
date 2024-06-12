package vn.com.ltdt.Coffee_Shop.product.dtos;

import java.util.List;

public record ProductListResponse(
    List<ProductDTO> content,
    int pageNo,
    int pageSize,
    long totalElement,
    int totalPage,
    boolean last
) {
}

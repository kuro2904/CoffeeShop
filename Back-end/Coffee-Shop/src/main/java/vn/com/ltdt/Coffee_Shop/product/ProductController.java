package vn.com.ltdt.Coffee_Shop.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDetailDTO;
import vn.com.ltdt.Coffee_Shop.product.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    // TODO: Fix this later

//    @GetMapping
//    public ResponseEntity<ProductListResponse> getAll(@RequestParam int page, @RequestParam int size) {
//        return new ResponseEntity<>(productService.getAllProducts(page, size), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO product) {
        log.info("Creating product {}", product);
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("{productId}/detail-add")
    public ResponseEntity<ProductDTO> addProductDetail(@PathVariable int productId,@Valid @RequestBody ProductDetailDTO req) {
        return new ResponseEntity<>(productService.addDetail(productId, req), HttpStatus.CREATED);
    }

    @PutMapping("{productId}/detail-remove/{detail-id}")
    public ResponseEntity<ProductDTO> removeProductDetail(@PathVariable int productId, @PathVariable(name = "detail-id") int detailId) {
        return new ResponseEntity<>(productService.removeDetail(productId, detailId), HttpStatus.OK);
    }

    @PutMapping("{productId}")
    public ResponseEntity<ProductDTO> updateProduct( @PathVariable int productId,@Valid @RequestBody ProductDTO product) {
        return new ResponseEntity<>(productService.updateProduct(productId, product), HttpStatus.OK);
    }

    @GetMapping("category")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@RequestParam(name = "categoryId") int categoryId) {
        log.info("Category Id : {}", categoryId);
        return new ResponseEntity<>(productService.getProductByCategory(categoryId), HttpStatus.OK);
    }

}

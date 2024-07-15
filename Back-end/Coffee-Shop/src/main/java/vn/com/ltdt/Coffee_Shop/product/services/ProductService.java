package vn.com.ltdt.Coffee_Shop.product.services;

import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDetailDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductListResponse;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(int id);
    ProductListResponse getAllProducts(int page, int size);
    ProductDTO addProduct(ProductDTO req);
    ProductDTO updateProduct(int id, ProductDTO req);
    List<ProductDTO> getAllProducts();
    ProductDTO addDetail(int id, ProductDetailDTO req);
    ProductDTO removeDetail(int id, int detailId);
    List<ProductDTO> getProductByCategory(int categoryId);
    List<ProductDTO> getProductByCategoryName(String name);
}

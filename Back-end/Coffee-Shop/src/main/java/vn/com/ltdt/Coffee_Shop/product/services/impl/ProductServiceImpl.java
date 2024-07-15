package vn.com.ltdt.Coffee_Shop.product.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.category.Category;
import vn.com.ltdt.Coffee_Shop.category.CategoryRepository;
import vn.com.ltdt.Coffee_Shop.exceptions.CoffeeShopException;
import vn.com.ltdt.Coffee_Shop.exceptions.ResourceNotFound;
import vn.com.ltdt.Coffee_Shop.images.Image;
import vn.com.ltdt.Coffee_Shop.images.ImageRepository;
import vn.com.ltdt.Coffee_Shop.utils.mappers.ProductMapper;
import vn.com.ltdt.Coffee_Shop.product.Product;
import vn.com.ltdt.Coffee_Shop.product.ProductDetail;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDetailDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductListResponse;
import vn.com.ltdt.Coffee_Shop.product.repositories.ProductRepository;
import vn.com.ltdt.Coffee_Shop.product.repositories.ProductDetailRepository;
import vn.com.ltdt.Coffee_Shop.product.services.ProductService;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ImageRepository imageRepository;


    @Override
    public ProductDTO getProductById(int id) {
        return productMapper.mapToDTO(productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product", "Id", String.valueOf(id))));
    }

    @Override
    public ProductListResponse getAllProducts(int page, int size) {
        return null;
    }

    @Override
    public ProductDTO addProduct(ProductDTO req) {
        validateRequest(req);
        Category category = categoryRepository.findById(req.categoryId())
                .orElseThrow(() -> new ResourceNotFound("Category", "Id", String.valueOf(req.categoryId())));

        Product product = mapToProduct(req, category);
        var savedProduct = productRepository.save(product);

        saveProductDetails(req, savedProduct);
        saveProductImages(req, savedProduct);

        log.info("Product with ID {} has been created successfully.", savedProduct.getId());
        return productMapper.mapToDTO(savedProduct);
    }

    private void validateRequest(ProductDTO req) {
        if (req.details() == null || req.details().isEmpty()) {
            throw new CoffeeShopException("Details are empty", HttpStatus.BAD_REQUEST);
        }
        if (req.images() == null || req.images().isEmpty()) {
            throw new CoffeeShopException("Images are empty", HttpStatus.BAD_REQUEST);
        }
    }

    private Product mapToProduct(ProductDTO req, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setName(req.name());
        product.setDescription(req.description());
        return product;
    }

    private void saveProductDetails(ProductDTO req, Product savedProduct) {
        req.details().forEach(d -> {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setProduct(savedProduct);
            productDetail.setPrice(d.price());
            productDetail.setSize(d.size());
            savedProduct.getProductDetails().add(productDetailRepository.save(productDetail));
        });
    }

    private void saveProductImages(ProductDTO req, Product savedProduct) {
        req.images().forEach(i -> {
            Image image = new Image();
            image.setProduct(savedProduct);
            image.setUrl(i);
            savedProduct.getImages().add(imageRepository.save(image));
        });
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO req) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product", "Id", String.valueOf(id)));
        Category category = categoryRepository.findById(req.categoryId()).orElseThrow(() -> new ResourceNotFound("Category", "Id", String.valueOf(id)));
        List<ProductDetail> details = new ArrayList<>();
        req.details().forEach(d ->{
            ProductDetail productDetail = productDetailRepository.findById(d.id()).orElseThrow(() -> new ResourceNotFound("ProductDetail", "Id", String.valueOf(d.id())));
            productDetail.setPrice(d.price());
            productDetail.setSize(d.size());
            details.add(productDetail);
        });
        product.setActive(req.isActive());
        product.setName(req.name());
        product.setDescription(req.description());
        product.setCategory(category);
        product.setProductDetails(details);
        return productMapper.mapToDTO(productRepository.save(product));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::mapToDTO).toList();
    }

    @Override
    public ProductDTO addDetail(int id, ProductDetailDTO req) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product", "Id", String.valueOf(id)));
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setPrice(req.price());
        productDetail.setSize(req.size());
        product.getProductDetails().add(productDetailRepository.save(productDetail));
        return productMapper.mapToDTO(product);
    }

    @Override
    public ProductDTO removeDetail(int id, int req) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product", "Id", String.valueOf(id)));
        ProductDetail detail = productDetailRepository.findById(req).orElseThrow(() -> new ResourceNotFound("ProductDetail", "Id", String.valueOf(req)));
        product.getProductDetails().remove(detail);
        productDetailRepository.delete(detail);
        return productMapper.mapToDTO(product);
    }

    @Override
    public List<ProductDTO> getProductByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "Id", String.valueOf(categoryId)));
        return productRepository.findByCategory(category).stream().map(productMapper::mapToDTO).toList();
    }

    @Override
    public List<ProductDTO> getProductByCategoryName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(() -> new ResourceNotFound("Category", "Name", String.valueOf(name)));
        return productRepository.findByCategory(category).stream().map(productMapper::mapToDTO).toList();
    }
}

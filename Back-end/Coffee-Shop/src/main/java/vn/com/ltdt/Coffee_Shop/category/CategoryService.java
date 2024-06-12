package vn.com.ltdt.Coffee_Shop.category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategories();
    CategoryDTO getCategory(int id);
    CategoryDTO addCategory(CategoryDTO category);
    CategoryDTO updateCategory(int id, CategoryDTO req);
    void deleteCategory(int id);
}

package vn.com.ltdt.Coffee_Shop.mappers;

import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.category.Category;
import vn.com.ltdt.Coffee_Shop.category.CategoryDTO;

@Service
public class CategoryMapper {

    public CategoryDTO mapToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(),category.getType().name());
    }

}

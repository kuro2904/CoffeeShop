package vn.com.ltdt.Coffee_Shop.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.exceptions.ResourceNotFound;
import vn.com.ltdt.Coffee_Shop.mappers.CategoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::mapToDTO).toList();
    }

    @Override
    public CategoryDTO getCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Category", "Id", String.valueOf(id)));
        return categoryMapper.mapToDTO(category);
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO req) {
        Category category = new Category();
        category.setName(req.name());
        category.setType(Type.valueOf(req.type()));
        return categoryMapper.mapToDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(int id, CategoryDTO req) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Category", "Id", String.valueOf(id)));
        category.setName(req.name());
        category.setType(Type.valueOf(req.type()));
        return categoryMapper.mapToDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Category", "Id", String.valueOf(id)));
        categoryRepository.delete(category);
    }


}

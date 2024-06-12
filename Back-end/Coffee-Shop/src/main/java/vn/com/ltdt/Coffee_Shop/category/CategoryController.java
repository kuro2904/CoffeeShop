package vn.com.ltdt.Coffee_Shop.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "categoryId") int id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.addCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable(name = "categoryId") int id, @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable(name = "categoryId") int id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}

package tt4.sasbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tt4.sasbackend.entity.Category;

import java.util.List;

@CrossOrigin
//@CrossOrigin(origins= {"http://intproj22.sit.kmutt.ac.th/tt4","http://localhost:5173/"})
@RestController
@RequestMapping("/api/categories")
public class categoryController {
    @Autowired
    private tt4.sasbackend.service.categoryService categoryService;
    @GetMapping("")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("{categoryId}")
    public Category getCategoryById(@PathVariable Integer categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
}

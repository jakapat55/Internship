package tt4.sasbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tt4.sasbackend.DTO.errorReportDetailsDTO;
import tt4.sasbackend.entity.Category;
import tt4.sasbackend.reposiotries.categoryRepo;
import java.util.ArrayList;
import java.util.List;

@Service
public class categoryService {
    @Autowired
    private categoryRepo repo;
    public List<Category> getAllCategory() {
        return repo.findAll();
    }

    public Category getCategoryById(Integer categoryId) {
        List<errorReportDetailsDTO> errorDetails = new ArrayList<>();
        try{
            return repo.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id " + categoryId + " does not exist"));
        }catch(RuntimeException e){
            errorDetails.add(new errorReportDetailsDTO("categoryId", "does not exists"));
            if (!errorDetails.isEmpty()) {throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, "Announcement attributes validation failed!", errorDetails);}
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Announcement attributes validation failed!", e);}
    }
    public Category createCategory(Category category){return repo.saveAndFlush(category);}

}

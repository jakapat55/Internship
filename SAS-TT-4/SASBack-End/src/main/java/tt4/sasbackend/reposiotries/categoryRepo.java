package tt4.sasbackend.reposiotries;

import org.springframework.data.jpa.repository.JpaRepository;
import tt4.sasbackend.entity.Category;

public interface categoryRepo extends JpaRepository<Category, Integer> {
}

package tt4.sasbackend.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class categoryDTO {
    @Id
    private Integer categoryId;
    private String categoryName;
    public String getCategoryName() {
        return categoryName;
    }

}

package tt4.sasbackend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class announcementByIdDTO {
    @Id
    private Integer id;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;

    public String getAnnouncementCategory() {
        return category == null ? "-" : category.getCategoryName();
    }

    @JsonIgnore
    private categoryDTO category;
    private Integer viewCount;

}

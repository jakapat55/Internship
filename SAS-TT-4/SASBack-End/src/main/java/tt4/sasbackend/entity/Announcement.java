package tt4.sasbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementId")
    private Integer id;
    @Column(name = "announcementTitle")
    private String announcementTitle;
    @Column(name = "announcementDescription")
    private String announcementDescription;
    @Column(name = "publishDate")
    private ZonedDateTime publishDate;
    @Column(name = "closeDate")
    private ZonedDateTime closeDate;
    @Column(name = "announcementDisplay")
    private String announcementDisplay;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category announcementCategory;
    private Integer viewCount;
}
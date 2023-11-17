package tt4.sasbackend.reposiotries;

import org.springframework.data.jpa.repository.JpaRepository;
import tt4.sasbackend.entity.Announcement;

public interface announcementRepo extends JpaRepository<Announcement, Integer> {
}

package tt4.sasbackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;
import tt4.sasbackend.DTO.errorReportDetailsDTO;
import tt4.sasbackend.DTO.announcementCreateUpdateDTO;
import tt4.sasbackend.entity.Announcement;
import tt4.sasbackend.entity.Category;
import tt4.sasbackend.reposiotries.announcementRepo;
import tt4.sasbackend.reposiotries.categoryRepo;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class announcementService {
    @Autowired
    private announcementRepo repo;
    @Autowired
    private categoryRepo categoryRepository;
    @Autowired
    private categoryService categoryService;
    public Announcement getAnnouncementById(Integer id, boolean count) {
        Announcement announcement = repo.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Can Not GET Announcement Id " + id + " Does Not Exist. [NOT_FOUND]"));
        int numOfCount = announcement.getViewCount();
        if (count == true){
            numOfCount++;
            announcement.setViewCount(numOfCount);
            repo.saveAndFlush(announcement);
        }
        return announcement;
    }
    public void deleteAnnouncementById(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Can Not DELETE Announcement Id " + id + " Does Not Exist. [BAD_REQUEST]");
        }
    }

    public Announcement updateAnnouncement(Integer id, announcementCreateUpdateDTO updateAnnouncementDTO) {
        Announcement announcement = repo.findById(id).orElseThrow(
                () -> new ResourceAccessException("Can Not UPDATE Announcement Id " +id + " Does Not Exist."));
        announcement.setAnnouncementTitle(updateAnnouncementDTO.getAnnouncementTitle());
        announcement.setAnnouncementDescription(updateAnnouncementDTO.getAnnouncementDescription());
        announcement.setPublishDate(updateAnnouncementDTO.getPublishDate());
        announcement.setCloseDate(updateAnnouncementDTO.getCloseDate());
        announcement.setAnnouncementDisplay(updateAnnouncementDTO.getAnnouncementDisplay());
        Category category = categoryRepository.findById(updateAnnouncementDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + updateAnnouncementDTO.getCategoryId()));
        announcement.setAnnouncementCategory(category);
        List<errorReportDetailsDTO> errorDetails = new ArrayList<>();
        Integer categoryId = updateAnnouncementDTO.getCategoryId();
        if (categoryId == null) {
            errorDetails.add(new errorReportDetailsDTO("categoryId", "must not be null"));
        }else{Category categoryCheck = categoryService.getCategoryById(categoryId);}
        String announcementDescription = updateAnnouncementDTO.getAnnouncementDescription();
        if ( announcementDescription == null) {
            errorDetails.add(new errorReportDetailsDTO("announcementDescription", "must not be null"));
        }else if (announcementDescription.length() > 10000 || announcementDescription.length() < 1 || announcementDescription.isBlank()) {
            errorDetails.add(new errorReportDetailsDTO("announcementDescription", "size must be between 1 and 10000"));}
        String display = updateAnnouncementDTO.getAnnouncementDisplay();
        if (display == null){
            announcement.setAnnouncementDisplay("N");
        }else if (!display.equals("Y") && !display.equals("N") ){
            errorDetails.add(new errorReportDetailsDTO("announcementDisplay", "must be either 'Y' or 'N'"));}
        String announcementTitle = updateAnnouncementDTO.getAnnouncementTitle();
        if (announcementTitle == null) {
            errorDetails.add(new errorReportDetailsDTO("announcementTitle", "must not be null"));
        }else if (announcementTitle.length() > 200 || announcementTitle.length() < 1 || announcementTitle.isBlank()) {
            errorDetails.add(new errorReportDetailsDTO("announcementTitle", "size must be between 1 and 200"));}
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime closeDate = updateAnnouncementDTO.getCloseDate();
        ZonedDateTime publishDate = updateAnnouncementDTO.getPublishDate();
        if (publishDate != null) {
            if (publishDate.isBefore(now)) {errorDetails.add(new errorReportDetailsDTO("publishDate", "must be a date in the present or in the future"));}
        }
        if (closeDate != null) {
            if (closeDate.isBefore(now)) {errorDetails.add(new errorReportDetailsDTO("closeDate", "must be a future date"));}
            if (publishDate != null && closeDate.isBefore(publishDate)){errorDetails.add(new errorReportDetailsDTO("closeDate", "must be later than publish date"));}
            if (publishDate != null && closeDate.equals(publishDate)){errorDetails.add(new errorReportDetailsDTO("closeDate", "must be later than publish date"));}
        }
        if (!errorDetails.isEmpty()) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, "Announcement attributes validation failed!", errorDetails);
        }else  {
            Category categorys = categoryRepository.findById(updateAnnouncementDTO.getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + categoryId));
            announcement.setAnnouncementCategory(categorys);
            return repo.saveAndFlush(announcement);
        }}
    public Page<Announcement> getAnnouncementWithPagination(int page, int pageSize, String mode, Integer categoryId) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        ZonedDateTime now = ZonedDateTime.now();
        List<Announcement> announcements = repo.findAll(Sort.by("id").descending());
        List<Announcement> filteredAnnouncements = new ArrayList<>();
        for (Announcement announcement : announcements) {
            if (announcement.getAnnouncementDisplay().equals("Y")) {
                if (mode.equals("close") && announcement.getCloseDate() != null) {
                    if (now.isAfter(announcement.getCloseDate())) {
                        if (categoryId != null) {
                            if (announcement.getAnnouncementCategory().equals(categoryRepository.findById(categoryId).get())) {
                                filteredAnnouncements.add(announcement);
                            }
                        } else if (categoryId == null) {
                            filteredAnnouncements.add(announcement);
                        }
                    }
                } else if (mode.equals("active")) {
                    if (announcement.getCloseDate() == null || now.isBefore(announcement.getCloseDate())) {
                        if (announcement.getPublishDate() == null || now.isAfter(announcement.getPublishDate()))
                            if (categoryId != null) {
                                if (announcement.getAnnouncementCategory().equals(categoryRepository.findById(categoryId).get())) {
                                    filteredAnnouncements.add(announcement);
                                }
                            } else if (categoryId == null) {
                                filteredAnnouncements.add(announcement);
                            }
                    }
                }
            }
        }

        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, filteredAnnouncements.size());
        List<Announcement> paginatedAnnouncements = filteredAnnouncements.subList(startIndex, endIndex);
        return new PageImpl<>(paginatedAnnouncements, pageRequest, filteredAnnouncements.size());
    }
    public List<Announcement> getAllAnnouncement(String mode) {
        try {
            List<Announcement> announcements = repo.findAll(Sort.by("id").descending());
            List<Announcement> filteredAnnouncements = new ArrayList<>();
            ZonedDateTime now = ZonedDateTime.now();
            for (Announcement announcement : announcements) {
                if (mode.equals("admin")) {
                    return announcements;
                } else if (announcement.getAnnouncementDisplay().equals("Y")) {
                    if (mode.equals("close") && announcement.getCloseDate() != null) {
                        if (now.isAfter(announcement.getCloseDate())) {
                            filteredAnnouncements.add(announcement);
                        }
                    } else if (mode.equals("active")) {
                        if (announcement.getCloseDate() == null || now.isBefore(announcement.getCloseDate())) {
                            if (announcement.getPublishDate() == null || now.isAfter(announcement.getPublishDate())) {
                                filteredAnnouncements.add(announcement);
                            }
                        }
                    }
                }
            }
            return filteredAnnouncements;
        } catch (Exception e) {
            String errorMessage = "An error occurred while retrieving announcements: " + e.getMessage();
            e.printStackTrace();
            throw new RuntimeException(errorMessage, e);
        }}
    public Announcement createAnnouncement(announcementCreateUpdateDTO announcementCreateDTO) {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementTitle(announcementCreateDTO.getAnnouncementTitle());
        announcement.setAnnouncementDescription(announcementCreateDTO.getAnnouncementDescription());
        announcement.setPublishDate(announcementCreateDTO.getPublishDate());
        announcement.setCloseDate(announcementCreateDTO.getCloseDate());
        announcement.setAnnouncementDisplay(announcementCreateDTO.getAnnouncementDisplay());
        announcement.setViewCount(0);
        List<errorReportDetailsDTO> errorDetails = new ArrayList<>();
        Integer categoryId = announcementCreateDTO.getCategoryId();
        if (categoryId == null) {
            errorDetails.add(new errorReportDetailsDTO("categoryId", "must not be null"));
        }else{Category categoryCheck = categoryService.getCategoryById(categoryId);}
        String announcementDescription = announcementCreateDTO.getAnnouncementDescription();
        if ( announcementDescription == null) {
            errorDetails.add(new errorReportDetailsDTO("announcementDescription", "must not be null"));
        }else if (announcementDescription.length() > 10000 || announcementDescription.length() < 1 || announcementDescription.isBlank()) {
            errorDetails.add(new errorReportDetailsDTO("announcementDescription", "size must be between 1 and 10000"));
        }
        String display = announcementCreateDTO.getAnnouncementDisplay();
        if (display == null){
            announcement.setAnnouncementDisplay("N");
        }else if (!display.equals("Y") && !display.equals("N") ){
            errorDetails.add(new errorReportDetailsDTO("announcementDisplay", "must be either 'Y' or 'N'"));
        }
        String announcementTitle = announcementCreateDTO.getAnnouncementTitle();
        if (announcementTitle == null) {
            errorDetails.add(new errorReportDetailsDTO("announcementTitle", "must not be null"));
        }else if (announcementTitle.length() > 200 || announcementTitle.length() < 1 || announcementTitle.isBlank()) {
            errorDetails.add(new errorReportDetailsDTO("announcementTitle", "size must be between 1 and 200"));}
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime closeDate = announcementCreateDTO.getCloseDate();
        ZonedDateTime publishDate = announcementCreateDTO.getPublishDate();
        if (publishDate != null) {
            if (publishDate.isBefore(now)) {
                errorDetails.add(new errorReportDetailsDTO("publishDate", "must be a date in the present or in the future"));
            }
        }
        if (closeDate != null) {
            if (closeDate.isBefore(now)){errorDetails.add(new errorReportDetailsDTO("closeDate", "must be a future date"));}
            if (publishDate != null && closeDate.isBefore(publishDate)){errorDetails.add(new errorReportDetailsDTO("closeDate", "must be later than publish date"));}
            if (publishDate != null && closeDate.equals(publishDate)){errorDetails.add(new errorReportDetailsDTO("closeDate", "must be later than publish date"));}
        }
        if (!errorDetails.isEmpty()) {
            throw new CustomResponseStatusException(HttpStatus.BAD_REQUEST, "Announcement attributes validation failed!", errorDetails);
        }else{
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + categoryId));
            announcement.setAnnouncementCategory(category);
            return repo.saveAndFlush(announcement);
        }
    }
}

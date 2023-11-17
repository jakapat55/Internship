package tt4.sasbackend.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tt4.sasbackend.DTO.*;
import tt4.sasbackend.ListMapper;
import tt4.sasbackend.entity.Announcement;
import tt4.sasbackend.service.CustomResponseStatusException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
//@CrossOrigin(origins= {"http://intproj22.sit.kmutt.ac.th/tt4","http://localhost:5173/"})
@RestController
@RequestMapping("/api/announcements")
public class announcementsController {
    @Autowired
    private tt4.sasbackend.service.announcementService announcementService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("")
    public List<announcementAllDTO> getAllAnnouncement(@RequestParam(value = "mode", defaultValue = "admin") String mode) {
        List<Announcement> announcements = announcementService.getAllAnnouncement(mode) ;
        List<announcementAllDTO> announcementDTOs = listMapper.mapList(announcements, announcementAllDTO.class, modelMapper);
        return announcementDTOs;
    }

    @GetMapping("/pages")
    public pageDTO<announcementAllDTO> getAnnouncementWithPaginationByCategory(
            @RequestParam(value = "mode", defaultValue = "active") String mode,
            @RequestParam(defaultValue = "0" ) Integer page,
            @RequestParam(defaultValue = "5")Integer pageSize,
            @RequestParam(defaultValue = "")Integer category){
        Page<Announcement> announcementsList = announcementService.getAnnouncementWithPagination(page,pageSize,mode,category);
        return listMapper.toPageDTO(announcementsList, announcementAllDTO.class, modelMapper);
    }

    @GetMapping("/{id}")
    public announcementByIdDTO getAnnouncementId(@PathVariable Integer id, @RequestParam(value = "count", defaultValue = "false") boolean count){
        return modelMapper.map(announcementService.getAnnouncementById(id,count), announcementByIdDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncementById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable Integer id, @RequestBody announcementCreateUpdateDTO announcementCreateDTO) {
        try {
            Announcement announcement = announcementService.updateAnnouncement(id, announcementCreateDTO);
            announcementCreateUpdateDTO announcementDTO = modelMapper.map(announcement, announcementCreateUpdateDTO.class);
            return new ResponseEntity<>(announcementDTO, HttpStatus.OK);
        } catch (CustomResponseStatusException ex) {
            List<errorReportDetailsDTO> errorDetails = ex.getErrorDetails();
            errorDetailsDTO response = new errorDetailsDTO(HttpStatus.BAD_REQUEST.value(), "Announcement attributes validation failed!", URI.create("uri=/api/announcements"), errorDetails);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("detail", errorDetails);
            responseBody.put("instance", response.getInstance());
            responseBody.put("title", response.getTitle());
            responseBody.put("status", response.getStatus());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createAnnouncement(@RequestBody announcementCreateUpdateDTO announcementCreateDTO) {
        try {
            Announcement announcement = announcementService.createAnnouncement(announcementCreateDTO);
            return ResponseEntity.ok(announcement);
        } catch (CustomResponseStatusException ex) {
            List<errorReportDetailsDTO> errorDetails = ex.getErrorDetails();
            errorDetailsDTO response = new errorDetailsDTO(HttpStatus.BAD_REQUEST.value(), "Announcement attributes validation failed!", URI.create("uri=/api/announcements"), errorDetails);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("detail", errorDetails);
            responseBody.put("instance", response.getInstance());
            responseBody.put("title", response.getTitle());
            responseBody.put("status", response.getStatus());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }
}

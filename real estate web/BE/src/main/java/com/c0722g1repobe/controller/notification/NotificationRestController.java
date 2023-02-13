package com.c0722g1repobe.controller.notification;

import com.c0722g1repobe.dto.notification.NotificationDeleteDto;
import com.c0722g1repobe.dto.notification.NotificationAllPropertyDto;
import com.c0722g1repobe.dto.notification.NotificationDto;
import com.c0722g1repobe.dto.notification.NotificationSearchDto;
import com.c0722g1repobe.entity.notification.Notification;
import com.c0722g1repobe.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/notifications")
public class NotificationRestController {
    @Autowired
    private INotificationService notificationService;

    /**
     * Create by: DatLA
     * Date created: 31/01/2023
     * Function: to search notifications by posting date and title and content
     *
     * @param notificationSearchDto
     * @param pageable
     * @return HttpStatus.NO_CONTENT if not found any notification /  HttpStatus.OK and Notifications with pagination if found
     */
    @PostMapping("/search")
    public ResponseEntity<Page<NotificationAllPropertyDto>> searchNotifications(@RequestBody NotificationSearchDto notificationSearchDto,
                                                                                @PageableDefault(value = 5) Pageable pageable) {
        if (notificationSearchDto == null || ObjectUtils.isEmpty(notificationSearchDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<NotificationAllPropertyDto> notificationPage = notificationService.searchNotifications(notificationSearchDto, pageable);
        if (notificationPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notificationPage, HttpStatus.OK);
    }


    /**
     * Create by: DatLA
     * Date created: 31/01/2023
     * Function: to find notifications by List ids
     *
     * @param idList
     * @return HttpStatus.NO_CONTENT if exists any notification not found or HttpStatus.OK and notification found
     */
    @PostMapping("/find-by-list-id")
    public ResponseEntity<List<NotificationDeleteDto>> findByListId(@RequestBody List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<NotificationDeleteDto> notificationDeleteDtoList = notificationService.findByListId(idList);
        if (idList.size() != notificationDeleteDtoList.size()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notificationDeleteDtoList, HttpStatus.OK);
    }

    /**
     * Create by DatLA
     * Date created: 31/01/2023
     * Function: to delete notifications by idList
     *
     * @param idList
     * @return HttpStatus.OK if remove successfully or HttpStatus.NO_CONTENT if exists not found notification
     * or HttpStatus.BAD_REQUEST if request is error
     */
    @PostMapping("/remove")
    public ResponseEntity<HttpStatus> remove(@RequestBody List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<NotificationDeleteDto> notificationDeleteDtoList = notificationService.findByListId(idList);
        if (idList.size() != notificationDeleteDtoList.size()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        notificationService.removeByListId(idList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Created by: AnhTDQ,
     * Date created: 03/02/2023
     * Function: create notification
     */
    @PostMapping("/create")
    public ResponseEntity<?> createNotification(@Valid @RequestBody NotificationDto notificationDto, BindingResult bindingResult) {
        Notification notification = new Notification();
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        notification.setTitle(notificationDto.getTitle());
        notification.setPostingDate(notificationDto.getPostingDate());
        notification.setContent(notificationDto.getContent());
        notification.setFlagDelete(notificationDto.isFlagDelete());
        notificationService.pushNotificationToDatabase(notification);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    /**
     * Created by: AnhTDQ,
     * Date created: 03/02/2023
     * Function: find notification by id
     */
    @GetMapping("findById/{id}")
    public ResponseEntity<Notification> findById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.findNotificationById(id);
        if (notification.isPresent()) {
            return new ResponseEntity<>(notification.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateNotification(@Valid @RequestBody NotificationDto notificationDto, BindingResult bindingResult,
                                                @PathVariable Long id) {
        new NotificationDto().validate(notificationDto, bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_MODIFIED);
        }
        Notification notification = notificationService.findNotificationById(id).get();
        notification.setIdNotification(id);
        notification.setContent(notificationDto.getContent());
        notification.setPostingDate(notificationDto.getPostingDate());
        notification.setTitle(notificationDto.getTitle());
        notificationService.updateNotificationTo(notification, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

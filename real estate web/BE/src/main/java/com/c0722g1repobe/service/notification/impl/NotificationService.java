package com.c0722g1repobe.service.notification.impl;

import com.c0722g1repobe.dto.notification.NotificationAllPropertyDto;
import com.c0722g1repobe.dto.notification.NotificationDeleteDto;
import com.c0722g1repobe.dto.notification.NotificationSearchDto;
import com.c0722g1repobe.entity.notification.Notification;
import com.c0722g1repobe.repository.notification.INotificationRepository;
import com.c0722g1repobe.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    /**
     * Create by: DatLA
     * Date created: 31/01/2023
     * Function: to get notification in page
     *
     * @param notificationSearchDto
     * @param pageable
     * @return notifications list with pagination
     */
    @Override
    public Page<NotificationAllPropertyDto> searchNotifications(NotificationSearchDto notificationSearchDto,
                                                                Pageable pageable) {
        return notificationRepository.searchNotifications(notificationSearchDto, pageable);
    }

    /**
     * Create by: DatLA
     * Date created: 31/01/2023
     * Function: to find notifications list by list of ids
     *
     * @param idList
     * @return List<NotificationDeleteDto>
     */
    @Override
    public List<NotificationDeleteDto> findByListId(List<Long> idList) {
        return notificationRepository.findByListId(idList);
    }

    /**
     * Create by: DatLA
     * Date created: 31/01/2023
     * Function: to delete notifications list by list of ids
     *
     * @param idList
     */
    @Override
    public void removeByListId(List<Long> idList) {
        notificationRepository.removeByListId(idList);
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: to create notification
     *
     * @param notification
     */
    @Override
    public void pushNotificationToDatabase(Notification notification) {
        notificationRepository.pushNotificationToDatabase(notification);
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: to update notification
     *
     * @param notification, id.
     */
    @Override
    public void updateNotificationTo(Notification notification, Long id) {
        notificationRepository.updateNotification(notification, id);
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: to find notification by id
     *
     * @return Optional<Notification>
     */
    @Override
    public Optional<Notification> findNotificationById(Long id) {
        return notificationRepository.findNotificationById(id);
    }
}

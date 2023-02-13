package com.c0722g1repobe.repository.notification;

import com.c0722g1repobe.dto.notification.NotificationAllPropertyDto;
import com.c0722g1repobe.dto.notification.NotificationDeleteDto;
import com.c0722g1repobe.dto.notification.NotificationSearchDto;
import com.c0722g1repobe.entity.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface INotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Create by: DatLA
     * Date created: 31/01/2023
     * Function: to search notifications by posting date and title and content
     *
     * @param notificationSearchDto
     * @param pageable
     * @return Notifications with pagination
     */
    @Query(value = "SELECT nt.`id_notification` AS idNotification, nt.posting_date AS postingDate, nt.`title` AS `title`, nt.`content` AS `content` " +
            " FROM `notification` AS nt " +
            " WHERE nt.flag_delete = 0 " +
            " AND nt.posting_date BETWEEN :#{#notificationSearchDto.startDate} AND NOW()" +
            " AND nt.`title` LIKE %:#{#notificationSearchDto.title}% " +
            " AND nt.`content` LIKE %:#{#notificationSearchDto.content}% " +
            " ORDER BY nt.posting_date DESC, nt.id_notification DESC "
            , nativeQuery = true)
    Page<NotificationAllPropertyDto> searchNotifications(@Param("notificationSearchDto") NotificationSearchDto notificationSearchDto,
                                                         Pageable pageable);

    /**
     * Create by: DatLA
     * Date created: 31/01/2023
     * Function: to find notification by List ids
     *
     * @param idList
     * @return notifications list
     */
    @Query(value = "SELECT `id_notification` AS idNotification, `title` FROM `notification` WHERE id_notification IN :idList AND flag_delete = 0", nativeQuery = true)
    List<NotificationDeleteDto> findByListId(@Param("idList") List<Long> idList);

    /**
     * Create by DatLA
     * Date created: 31/01/2023
     * Function: remove the list of notifications by id by updating the flag_deleted attribute.
     *
     * @param idList
     */
    @Modifying
    @Query(value = "UPDATE `notification` SET flag_delete = 1 WHERE id_notification IN :idList", nativeQuery = true)
    void removeByListId(@Param("idList") List<Long> idList);

    /**
     * Create by: AnhTDQ
     * Date created: 31/01/2023
     * Function: create new Notification
     *
     * @param notification
     */
    @Modifying
    @Query(value = "insert into `notification` (id_notification, title,posting_date,content,flag_delete)" +
            " value (:#{#notification.idNotification}, :#{#notification.title} , :#{#notification.postingDate} , :#{#notification.content}, :#{#notification.flagDelete} )", nativeQuery = true)
    void pushNotificationToDatabase(@Param("notification") Notification notification);


    /**
     * Create by: AnhTDQ
     * Date created: 31/01/2023
     * Function: find notification by id
     *
     * @param notification, id
     */
    @Modifying
    @Query(value = "update `notification` set `title` = :#{#notification.title} ,posting_date = :#{#notification.postingDate} ,content = :#{#notification.content} , flag_delete = :#{#notification.flagDelete} " +
            " where  (id_notification = :idUpdate)", nativeQuery = true)
    void updateNotification(@Param("notification") Notification notification, @Param("idUpdate") Long id);

    /**
     * Create by: AnhTDQ
     * Date created: 31/01/2023
     * Function: find notification by id
     *
     * @param id
     * @return Optional<Notification>
     */
    @Query(value = "select nt.`id_notification`, nt.`title` as `title` ,nt.`posting_date`,nt.`content`, nt.`flag_delete` from `notification` as nt where id_notification = :id ", nativeQuery = true)
    Optional<Notification> findNotificationById(@Param("id") Long id);
}

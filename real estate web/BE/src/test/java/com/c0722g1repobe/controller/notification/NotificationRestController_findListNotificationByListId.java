package com.c0722g1repobe.controller.notification;


import com.c0722g1repobe.dto.notification.NotificationDeleteDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationRestController_findListNotificationByListId {
    @Autowired
    private NotificationRestController notificationRestController;

    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: notification list search by list id with list id are null
     *
     * @throws Exception
     */

    @Test
    public void findListNotificationByListId_29() throws Exception {
        ResponseEntity<List<NotificationDeleteDto>> responseEntity
                = this.notificationRestController.findByListId(null);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }
    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: notification list search by list id with list id are empty
     *
     * @throws Exception
     */

    @Test
    public void findListNotificationByListId_30() throws Exception {
        ResponseEntity<List<NotificationDeleteDto>> responseEntity
                = this.notificationRestController.findByListId(Collections.emptyList());
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: notification list search by list id with value does not exist in database
     *
     * @throws Exception
     */
    @Test
    public void findListNotificationByListId_31() throws Exception {
        List<Long> listIds = Arrays.asList(20L, 21L);
        ResponseEntity<List<NotificationDeleteDto>> responseEntity
                = this.notificationRestController.findByListId(listIds);
        Assertions.assertEquals(204, responseEntity.getStatusCodeValue());
    }

    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: notification list search by list id with correct id value
     *
     * @throws Exception
     */
    @Test
    public void findListNotificationByListId_32() throws Exception {
        List<Long> listIds = Arrays.asList(1L, 2L);
        ResponseEntity<List<NotificationDeleteDto>> responseEntity
                = this.notificationRestController.findByListId(listIds);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, Objects.requireNonNull(responseEntity.getBody()).size());
        Assertions.assertEquals("Thông báo đầu tiên",
                responseEntity.getBody().get(0).getTitle());
    }
}

package com.c0722g1repobe.controller.notification;

import com.c0722g1repobe.controller.notification.NotificationRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationRestController_deleteNotificationByListId {
    @Autowired
    private NotificationRestController notificationRestController;

    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: delete notification by list id with list id are null
     *
     * @throws Exception
     */
    @Test
    public void deleteNotificationsByListId_25() throws Exception {
        ResponseEntity<HttpStatus> responseEntity
                = this.notificationRestController.remove(null);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: delete notification by list id with list id are empty
     *
     * @throws Exception
     */
    @Test
    public void deleteNotificationsByListId_26() throws Exception {
        ResponseEntity<HttpStatus> responseEntity
                = this.notificationRestController.remove(Collections.emptyList());
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: delete notifications with id value does not exist in database
     *
     * @throws Exception
     */
    @Test
    public void deleteNotificationsByListId_27() throws Exception {
        List<Long> listIds = Arrays.asList(20L, 21L);
        ResponseEntity<HttpStatus> responseEntity
                = this.notificationRestController.remove(listIds);
        Assertions.assertEquals(204, responseEntity.getStatusCodeValue());
    }

    /**
     * Created by: DatLA,
     * Date created: 01/02/2023
     * Function: delete notifications by list id with correct id value
     *
     * @throws Exception
     */
    @Test
    public void deleteNotificationsByListId_28() throws Exception {
        List<Long> listIds = Arrays.asList(1L, 2L);
        ResponseEntity<HttpStatus> responseEntity
                = this.notificationRestController.remove(listIds);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
    }
}

package com.c0722g1repobe.controller.notification;

import com.c0722g1repobe.controller.notification.NotificationRestController;
import com.c0722g1repobe.dto.notification.NotificationAllPropertyDto;
import com.c0722g1repobe.dto.notification.NotificationSearchDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationRestController_getListNotification {
    @Autowired
    private NotificationRestController notificationRestController;

    /**
     * Created by: DatLA
     * Date created: 01/02/2023
     * Function: get notification listing with pagination and dto object to search is null
     *
     * @throws Exception
     */
    @Test
    public void getListNotification_33() throws Exception {
        ResponseEntity<Page<NotificationAllPropertyDto>> responseEntity
                = this.notificationRestController.searchNotifications(null, PageRequest.of(0, 5));
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    /**
     * Created by: DatLA
     * Date created: 01/02/2023
     * Function: get notification listing with pagination and dto object to search is empty
     *
     * @throws Exception
     */
    @Test
    public void getListNotification_34() throws Exception {
        NotificationSearchDto notificationSearchDto = new NotificationSearchDto("", "", "");
        ResponseEntity<Page<NotificationAllPropertyDto>> responseEntity
                = this.notificationRestController.searchNotifications(notificationSearchDto, PageRequest.of(0, 5));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(4, Objects.requireNonNull(responseEntity.getBody()).getTotalPages());
        Assertions.assertEquals(19, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("2023-01-24",
                responseEntity.getBody().getContent().get(0).getPostingDate());
        Assertions.assertEquals("V??? vi???c thay ?????i ?????a ch???",
                responseEntity.getBody().getContent().get(0).getTitle());
        Assertions.assertEquals("C??ng ty th??ng b??o ?????n to??n th??? nh??n vi??n v??? vi???c thay ?????i ?????a ch??? v??n ph??ng t??? ng??y 15/02/2023." +
                        "C??ng ty chuy???n sang ?????a ch??? m???i: ???????ng Tr???n H??ng ?????o, S??n Tr??, ???? N???ng. Tr??n tr???ng.",
                responseEntity.getBody().getContent().get(0).getContent());
    }

    /**
     * Created by: DatLA
     * Date created: 01/02/2023
     * Function: get pagination notification list with dto object to search is does not exist in database
     *
     * @throws Exception
     */
    @Test
    public void getListNotification_35() throws Exception {
        NotificationSearchDto notificationSearchDto = new NotificationSearchDto("2025-01-01", "", "");
        ResponseEntity<Page<NotificationAllPropertyDto>> responseEntity
                = this.notificationRestController.searchNotifications(notificationSearchDto, PageRequest.of(0, 5));
        Assertions.assertEquals(204, responseEntity.getStatusCodeValue());
    }

    /**
     * Created by: DatLA
     * Date created: 01/02/2023
     * Function: get pagination notification list with dto object to search is exist in database
     *
     * @throws Exception
     */
    @Test
    public void getListNotification_36() throws Exception {
        NotificationSearchDto notificationSearchDto = new NotificationSearchDto("2022-06-01", "Th??ng b??o ?????u ti??n", "N???i dung th??ng b??o: Tr??n tr???ng th??ng b??o website \"Qu???n l?? m??i gi???i b???t ?????ng s???n\" ch??nh th???c ??i v??o ho???t ?????ng.");
        ResponseEntity<Page<NotificationAllPropertyDto>> responseEntity
                = this.notificationRestController.searchNotifications(notificationSearchDto, PageRequest.of(0, 5));
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("2022-06-01",
                responseEntity.getBody().getContent().get(0).getPostingDate());
        Assertions.assertEquals("Th??ng b??o ?????u ti??n",
                responseEntity.getBody().getContent().get(0).getTitle());
        Assertions.assertEquals("N???i dung th??ng b??o: Tr??n tr???ng th??ng b??o website \"Qu???n l?? m??i gi???i b???t ?????ng s???n\" ch??nh th???c ??i v??o ho???t ?????ng.",
                responseEntity.getBody().getContent().get(0).getContent());
    }
}

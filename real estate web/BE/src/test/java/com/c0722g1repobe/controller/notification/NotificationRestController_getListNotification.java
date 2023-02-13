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
        Assertions.assertEquals("Về việc thay đổi địa chỉ",
                responseEntity.getBody().getContent().get(0).getTitle());
        Assertions.assertEquals("Công ty thông báo đến toàn thể nhân viên về việc thay đổi địa chỉ văn phòng từ ngày 15/02/2023." +
                        "Công ty chuyển sang địa chỉ mới: Đường Trần Hưng Đạo, Sơn Trà, Đà Nẵng. Trân trọng.",
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
        NotificationSearchDto notificationSearchDto = new NotificationSearchDto("2022-06-01", "Thông báo đầu tiên", "Nội dung thông báo: Trân trọng thông báo website \"Quản lý môi giới bất động sản\" chính thức đi vào hoạt động.");
        ResponseEntity<Page<NotificationAllPropertyDto>> responseEntity
                = this.notificationRestController.searchNotifications(notificationSearchDto, PageRequest.of(0, 5));
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("2022-06-01",
                responseEntity.getBody().getContent().get(0).getPostingDate());
        Assertions.assertEquals("Thông báo đầu tiên",
                responseEntity.getBody().getContent().get(0).getTitle());
        Assertions.assertEquals("Nội dung thông báo: Trân trọng thông báo website \"Quản lý môi giới bất động sản\" chính thức đi vào hoạt động.",
                responseEntity.getBody().getContent().get(0).getContent());
    }
}

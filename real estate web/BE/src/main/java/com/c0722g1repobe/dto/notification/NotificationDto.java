package com.c0722g1repobe.dto.notification;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class NotificationDto implements Validator {

    /**
     * Created by: AnhTDQ,
     * Date created: 03/02/2023
     * Function: validator data
     */
    private Long idNotification;
    private LocalDate postingDate;
    private String title;
    private String content;
    private boolean flagDelete = false;

    public NotificationDto() {
    }

    public NotificationDto(Long idNotification, LocalDate postingDate, String title, String content, boolean flagDelete) {
        this.idNotification = idNotification;
        this.postingDate = postingDate;
        this.title = title;
        this.content = content;
        this.flagDelete = flagDelete;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        NotificationDto notificationDto = (NotificationDto) target;

        if (notificationDto.getTitle().equals("")) {
            errors.rejectValue("title", "title", "Tiêu đề không được bỏ trống");
        }

        if (notificationDto.getContent().equals("")) {
            errors.rejectValue("content", "content", "Nội dung không được bỏ trống");
        }

    }


}

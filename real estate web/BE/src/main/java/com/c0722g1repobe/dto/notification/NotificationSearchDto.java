package com.c0722g1repobe.dto.notification;

/**
 * Create by: DatLA
 * Date created: 02/02/2023
 * For: to get search object
 */
public class NotificationSearchDto {
    private String startDate;
    private String title;
    private String content;

    public NotificationSearchDto() {
    }

    public NotificationSearchDto(String startDate, String title, String content) {
        this.startDate = startDate;
        this.title = title;
        this.content = content;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

}

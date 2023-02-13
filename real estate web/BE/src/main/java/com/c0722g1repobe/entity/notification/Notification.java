package com.c0722g1repobe.entity.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;
    private LocalDate postingDate;
    @Column(columnDefinition = "varchar(60)")
    private String title;
    @Column(columnDefinition = "varchar(450)")
    private String content;
    private boolean flagDelete = false;
}

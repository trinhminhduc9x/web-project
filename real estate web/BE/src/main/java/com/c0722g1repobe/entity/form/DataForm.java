package com.c0722g1repobe.entity.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDataForm;
    private String contentDataForm;
    private boolean flagDelete = false;
    @Column(length = 100000000)
    private String urlDataForm;
}

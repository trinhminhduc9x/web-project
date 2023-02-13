package com.c0722g1repobe.entity.post;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistrict;
    private String nameDistrict;
    @ManyToOne
    private City city;
}

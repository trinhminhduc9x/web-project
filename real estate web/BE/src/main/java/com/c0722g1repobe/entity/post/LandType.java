package com.c0722g1repobe.entity.post;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LandType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLandType;
    private String nameLandType;
}

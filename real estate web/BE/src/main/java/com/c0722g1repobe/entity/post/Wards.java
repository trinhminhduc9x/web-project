package com.c0722g1repobe.entity.post;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWards;
    private String nameWards;
    @ManyToOne
    private District district;
}

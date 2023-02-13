package com.c0722g1repobe.dto.post.create_post;

import lombok.*;

import java.util.List;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePostDto {
    private Long idCustomer;
    private String namePost;
    private Long idDemand;
    private Long idLandType;
    private Long idWards;
    private Long idDirection;
    private String numberAddress;
    private Double price;
    private Double area;
    private String note;
    private String[] imageListURL;
}

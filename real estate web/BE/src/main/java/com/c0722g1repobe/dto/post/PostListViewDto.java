package com.c0722g1repobe.dto.post;

import java.util.List;
import java.util.Set;

public interface PostListViewDto {
    Long getIdPost();
    String getNamePost();
    Double getPrice();
    Double getArea();
    String getDistrict();
    String getCity();
    List<String> url();
    String getDateCreation();
    String getNameLandType();
    String getNameDirection();
    String getIdDemandType();
}

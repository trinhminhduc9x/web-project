package com.c0722g1repobe.dto.post;

import java.time.LocalDate;

public interface PostDetailDto {

    Long getIdPost();

    String getNamePost();

    String getNote();

    Double getArea();

    Double getPrice();

    LocalDate getDateCreation();

    String getNameDirection();

    String getNameStatusPost();

    String getNumberAddress();

    String getNameDemandType();

    String getNameLandType();

    String getNameWards();

    String getNameDistrict();

    String getNameCity();

    String getNameCustomer();

    String getEmailCustomer();

    String getPhoneCustomer1();

    int getGenderCustomer();

    Long getIdCustomer();

    Long getIdDemandType();

    boolean isApproval();

    boolean isFlagDeleted();

}
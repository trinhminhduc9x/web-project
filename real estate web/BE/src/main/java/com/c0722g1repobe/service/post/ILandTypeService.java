package com.c0722g1repobe.service.post;

import com.c0722g1repobe.entity.post.LandType;

import java.util.List;

public interface ILandTypeService {

    /**
     * Create by: SangNP
     * Date created: 31/01/2023
     * Function: take list Land Type
     * @return List<LandType>
     */
    List<LandType> findAll();
}

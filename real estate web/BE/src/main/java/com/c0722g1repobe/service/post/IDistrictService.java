package com.c0722g1repobe.service.post;

import com.c0722g1repobe.entity.post.District;

import java.util.List;

public interface IDistrictService {
    List<District> findNameDistrictAndIdDistrictQuery();

    String findNameByIdNativeQuery(Long idDistrict);
    List<District> findListDistrict(Long idCity);

}

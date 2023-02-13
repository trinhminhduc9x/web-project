package com.c0722g1repobe.service.post;

import com.c0722g1repobe.entity.post.Wards;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWardsService {

    List<Wards> findNameWardsAndIdWardsQuery();

    String findNameByIdNativeQuery(Long idWards);
    List<Wards> findListWards(Long idDistrict);
}

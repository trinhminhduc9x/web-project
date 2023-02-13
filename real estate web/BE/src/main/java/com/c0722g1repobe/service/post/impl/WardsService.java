package com.c0722g1repobe.service.post.impl;

import com.c0722g1repobe.entity.post.Wards;
import com.c0722g1repobe.repository.post.IWardsRepository;
import com.c0722g1repobe.service.post.IWardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardsService implements IWardsService {
    /**
     * DI IWardsRepository to use IWardsRepository's methods;
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Autowired
    private IWardsRepository wardsRepository;
    /**
     * Call method findNameWardsAndIdWardsQuery() of IWardsRepository
     * @return : List<Wards> from IWardsRepository
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Override
    public List<Wards> findNameWardsAndIdWardsQuery() {
        return wardsRepository.findNameWardsAndIdWardsQuery();
    }
    /**
     * Call method findNameByIdNativeQuery() of IWardsRepository
     * @return : nameDistrict from IWardsRepository
     * @param : idWards
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Override
    public String findNameByIdNativeQuery(Long idWards) {
        return wardsRepository.findNameByIdNativeQuery(idWards);
    }
    /**
     * Create by: NgocLV
     * Date Create: 03/02/2023
     * Description: find list wards .
     *
     * @return  list wards or null if not found
     */
    @Override
    public List<Wards> findListWards(Long idDistrict) {
        return wardsRepository.findListWards(idDistrict);
    }
}

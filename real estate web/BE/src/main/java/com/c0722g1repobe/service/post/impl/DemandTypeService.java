package com.c0722g1repobe.service.post.impl;

import com.c0722g1repobe.entity.post.DemandType;
import com.c0722g1repobe.repository.post.IDemandTypeRepository;
import com.c0722g1repobe.service.post.IDemandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandTypeService implements IDemandTypeService {
    @Autowired
    IDemandTypeRepository demandTypeRepository;
    /**
     * Create by: NgocLV
     * Date Create: 03/02/2023
     * Description: find list demandType .
     *
     * @return  list demandType or null if not found
     */
    @Override
    public List<DemandType> getAllDemandType() {
        return demandTypeRepository.getAllDemandType();
    }
}

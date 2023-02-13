package com.c0722g1repobe.service.post.impl;

import com.c0722g1repobe.entity.post.LandType;
import com.c0722g1repobe.repository.post.ILandTypeRepository;
import com.c0722g1repobe.service.post.ILandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandTypeService implements ILandTypeService {
    @Autowired
    private ILandTypeRepository landTypeRepository;

    @Override
    public List<LandType> findAll() {
        return landTypeRepository.findAllNativeQuery();
    }
}

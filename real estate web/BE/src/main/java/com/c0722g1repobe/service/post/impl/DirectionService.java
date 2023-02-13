package com.c0722g1repobe.service.post.impl;

import com.c0722g1repobe.entity.post.Direction;
import com.c0722g1repobe.repository.post.IDirectionRepository;
import com.c0722g1repobe.service.post.IDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionService implements IDirectionService {
    @Autowired
    private IDirectionRepository directionRepository;

    @Override
    public List<Direction> findAll() {
        return directionRepository.findAllNativeQuery();
    }

}

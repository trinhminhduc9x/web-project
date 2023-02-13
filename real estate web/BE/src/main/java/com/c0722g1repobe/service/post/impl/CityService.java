package com.c0722g1repobe.service.post.impl;

import com.c0722g1repobe.entity.post.City;
import com.c0722g1repobe.repository.post.ICityRepository;
import com.c0722g1repobe.service.post.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {
    @Autowired
    ICityRepository cityRepository;

    @Override
    public List<City> listCity() {
        return cityRepository.listCity();
    }
}

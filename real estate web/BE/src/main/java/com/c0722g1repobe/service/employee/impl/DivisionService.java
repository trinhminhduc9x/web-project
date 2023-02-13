package com.c0722g1repobe.service.employee.impl;

import com.c0722g1repobe.entity.employee.Division;
import com.c0722g1repobe.repository.employee.IDivisionRepository;
import com.c0722g1repobe.service.employee.IDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService implements IDivisionService {

    @Autowired
    private IDivisionRepository divisionRepository;

    /**
     * Create by: NhanUQ
     * Date created: 02/02/2023
     * Function: get list division
     *
     * @return json list division
     */
    @Override
    public List<Division> getAllDivision() {
        return divisionRepository.getAllDivision();
    }
}

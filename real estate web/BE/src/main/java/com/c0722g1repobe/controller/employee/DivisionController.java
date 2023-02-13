package com.c0722g1repobe.controller.employee;

import com.c0722g1repobe.entity.employee.Division;
import com.c0722g1repobe.service.employee.IDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/divisions")
@CrossOrigin("*")
public class DivisionController {

    @Autowired
    private IDivisionService divisionService;

    /**
     * Create by: NhanUQ
     * Date created: 02/02/2023
     * Function: show list division
     *
     * @return HttpStatus.OK if result a list division
     */
    @GetMapping("")
    public ResponseEntity<List<Division>> getAllDivision() {
        List<Division> divisionList = divisionService.getAllDivision();
        return new ResponseEntity<>(divisionList, HttpStatus.OK);
    }
}

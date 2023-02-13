package com.c0722g1repobe.controller.post;

import com.c0722g1repobe.entity.post.LandType;
import com.c0722g1repobe.service.post.ILandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/land-type")
public class LandTypeRestController {
    @Autowired
    private ILandTypeService landTypeService;

    /**
     * Create by: SangNP
     * Date created: 31/01/2023
     * Function: take list Land Type
     * @return List<LandType>
     */
    @GetMapping("")
    public ResponseEntity<List<LandType>> findAllLandType(){
        List<LandType> landTypeList = landTypeService.findAll();
        return new ResponseEntity<>(landTypeList, HttpStatus.OK);
    }
}

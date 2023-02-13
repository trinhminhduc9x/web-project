package com.c0722g1repobe.controller.post;

import com.c0722g1repobe.entity.post.DemandType;
import com.c0722g1repobe.service.post.IDemandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/demand-type")
@CrossOrigin("*")
public class DemandTypeRestController {
    @Autowired
    IDemandTypeService demandTypeService;
    /**
     * Create by: NgocLV
     * Date created: 02/02/2023
     * Function: list demandType
     *
     * @return HttpStatus.OK if json list Post
     */
    @GetMapping("")
    public ResponseEntity<List<DemandType>> getListDemandType(){
        List<DemandType> demandTypeList = demandTypeService.getAllDemandType();
        if (demandTypeList.isEmpty()) {
            return new ResponseEntity<List<DemandType>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<DemandType>>(demandTypeList, HttpStatus.OK);
    }
}

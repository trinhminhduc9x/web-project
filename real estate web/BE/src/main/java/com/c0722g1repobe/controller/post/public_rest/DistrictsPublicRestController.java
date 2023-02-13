package com.c0722g1repobe.controller.post.public_rest;

import com.c0722g1repobe.entity.post.District;
import com.c0722g1repobe.service.post.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/public/districts")
public class DistrictsPublicRestController {
    /**
     * DI IDistrictService to use IDistrictService's methods;
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Autowired
    private IDistrictService districtService;
    /**
     * Method use: getDistrictList()=> call method findNameDistrictAndIdDistrictQuery() of IDistrictService to get list data from database
     * Use ResponseEntity to handling response, datatype: List<District>
     * @param : No
     * @return : If the list returned is an empty list, return http status code : HttpStatus.NO_CONTENT;
     * @return : If the list returned is a list with data, then return http status code: HttpStatus.OK and List<District>
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @GetMapping("")
    public ResponseEntity<List<District>> getDistrictList() {
        List<District> districtList = districtService.findNameDistrictAndIdDistrictQuery();
        if (districtList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(districtList, HttpStatus.OK);
    }
}

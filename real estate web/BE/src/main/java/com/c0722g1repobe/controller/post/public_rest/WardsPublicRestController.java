package com.c0722g1repobe.controller.post.public_rest;

import com.c0722g1repobe.entity.post.Wards;
import com.c0722g1repobe.service.post.IWardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/public/wards")
public class WardsPublicRestController {
    /**
     * DI IWardsService to use IWardsService's methods;
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Autowired
    private IWardsService wardsService;

    /**
     * Method use: getWardsList()=> call method findNameWardsAndIdWardsQuery() of IWardsService to get list data from database
     * Use ResponseEntity to handling response, datatype: List<Wards>
     * @param : No
     * @return : If the list returned is an empty list, return http status code : HttpStatus.NO_CONTENT;
     * @return : If the list returned is a list with data, then return http status code: HttpStatus.OK and List<Wards>
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @GetMapping("")
    public ResponseEntity<List<Wards>> getWardsList() {
        List<Wards> wardsList = wardsService.findNameWardsAndIdWardsQuery();
        if (wardsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wardsList, HttpStatus.OK);
    }
}

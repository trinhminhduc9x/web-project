package com.c0722g1repobe.controller.post;

import com.c0722g1repobe.entity.post.City;
import com.c0722g1repobe.service.post.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("api/city")
@CrossOrigin("*")
public class CityRestController {
    @Autowired
    ICityService cityService;
    /**
     * Create by: NgocLV
     * Date created: 02/02/2023
     * Function: list city
     *
     * @return HttpStatus.OK if json list Post
     */
    @GetMapping("")
    public ResponseEntity<List<City>> listCity(){
       List<City> listCity = cityService.listCity();
        if (listCity.isEmpty()) {
        return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
    }
        return new ResponseEntity<List<City>>(listCity, HttpStatus.OK);
    }

}

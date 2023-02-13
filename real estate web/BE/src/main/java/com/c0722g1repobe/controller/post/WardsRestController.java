package com.c0722g1repobe.controller.post;

import com.c0722g1repobe.entity.post.Wards;
import com.c0722g1repobe.service.post.IWardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/wards")
public class WardsRestController {
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
    /**
     * Create by: NgocLV
     * Date created: 02/02/2023
     * Function: list wards
     *
     * @return HttpStatus.OK if json list wards
     */
    @GetMapping("/list")
    public ResponseEntity<List<Wards>> listDistrict(@RequestParam() Optional<Long> idDistrict){
        Long idDistrictValue = idDistrict.orElse(0l);
        List<Wards> listWards = null;
        if(idDistrictValue != 0l) {
            listWards = wardsService.findListWards(idDistrictValue);
        }
        else {
            return new ResponseEntity<List<Wards>>(HttpStatus.NO_CONTENT);
        }

        if (listWards.isEmpty()) {
            return new ResponseEntity<List<Wards>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Wards>>(listWards, HttpStatus.OK);
    }
}

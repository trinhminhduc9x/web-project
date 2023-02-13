package com.c0722g1repobe.controller.post.public_rest;

import com.c0722g1repobe.entity.post.Direction;
import com.c0722g1repobe.service.post.IDirectionService;
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
@RequestMapping("api/public/direction")
public class DirectionPublicRestController {
    @Autowired
    private IDirectionService directionService;

    /**
     * Create by: SangNP
     * Date created: 31/01/2023
     * Function: take list direction
     * @return List<Direction>
     */
    @GetMapping("")
    public ResponseEntity<List<Direction>> findAll() {
        List<Direction> directionList = directionService.findAll();
        return new ResponseEntity<>(directionList, HttpStatus.OK);
    }
}

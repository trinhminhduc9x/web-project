package com.c0722g1repobe.controller.post;

import com.c0722g1repobe.dto.post.ImageDto;
import com.c0722g1repobe.entity.post.Image;
import com.c0722g1repobe.service.post.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/image")
public class ImageRestController {

    @Autowired
    private IImageService imageService;

    @GetMapping("")
    public ResponseEntity<List<ImageDto>> findImageByIdPost(@RequestParam Long id) {
        List<ImageDto> imageList = imageService.findImageByIdPost(id);
        if (imageList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(imageList, HttpStatus.OK);
    }
}

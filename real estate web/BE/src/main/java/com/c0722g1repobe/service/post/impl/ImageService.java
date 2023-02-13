package com.c0722g1repobe.service.post.impl;

import com.c0722g1repobe.dto.post.ImageDto;
import com.c0722g1repobe.entity.post.Image;
import com.c0722g1repobe.repository.post.IImageRepository;
import com.c0722g1repobe.service.post.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Override
    public List<ImageDto> findImageByIdPost(Long id) {
        return imageRepository.findImageByIdPost(id);
    }
}

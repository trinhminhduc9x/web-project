package com.c0722g1repobe.service.post;

import com.c0722g1repobe.dto.post.ImageDto;
import com.c0722g1repobe.entity.post.Image;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImageService {
    /**
     * Method uses:
     * Get a image list from database where image's idPost = parameter id
     * Created by: HuyDN
     * Created date: 04/02/2023
     *
     * @param: id: a Post' id
     * @return: a image list
     */
    List<ImageDto> findImageByIdPost(@Param("id") Long id);
}

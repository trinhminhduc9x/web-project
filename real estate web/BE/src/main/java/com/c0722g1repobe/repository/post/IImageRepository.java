package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.dto.post.ImageDto;
import com.c0722g1repobe.entity.post.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    /**
     * Method uses:
     * Get a image list from database where image's idPost = parameter id
     * Created by: HuyDN
     * Created date: 04/02/2023
     *
     * @param: id: a Post' id
     * @return: a image list
     */
    @Query(value = "select image.id_image as idImage, image.url from image where image.post_id_post = :id", nativeQuery = true)
    List<ImageDto> findImageByIdPost(@Param("id") Long id);

    /**
     * Created by : BaoDP
     * Created date: 06/02/2023
     * Description: add method save image to image table (updated in database ver 3)
     *
     * @param: image
     */
    @Modifying
    @Query(value = "insert into image (url, post_id_post) values (:url, :id)",nativeQuery = true)
    @Transactional
    void saveImage(@Param("url") String url, @Param("id") Long idPost);
}

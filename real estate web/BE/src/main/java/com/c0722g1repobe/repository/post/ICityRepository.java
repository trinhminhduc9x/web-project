package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.entity.post.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICityRepository extends JpaRepository<City, Long> {
    /**
     * Create by: NgocLV
     * Date created: 02/02/2023
     * Function: find all city list
     *
     */

    @Query(value = "select * from sprint_1.city",
            nativeQuery = true)
    List<City> listCity();
}

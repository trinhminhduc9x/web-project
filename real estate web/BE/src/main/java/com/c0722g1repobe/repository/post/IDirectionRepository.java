package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.entity.post.Direction;
import com.c0722g1repobe.entity.post.LandType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDirectionRepository extends JpaRepository<Direction, Long> {
    @Query(value = "select * from sprint_1.direction as d where d.id_direction = :idDirection", nativeQuery = true)
    Direction findByIdNativeQuery(@Param("idDirection") Long idDirection);

    /**
     * Create by : SangNP
     * Date create: 02/02/2023
     * Description: take direction list
     * @return List<Direction>
     */
    @Query(value = "select * from sprint_1.direction", nativeQuery = true)
    List<Direction> findAllNativeQuery();
}

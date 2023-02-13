package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.entity.post.LandType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILandTypeRepository extends JpaRepository<LandType, Long> {
    @Query(value = "select * from sprint_1.land_type as lt where lt.id_land_type = :idLandType", nativeQuery = true)
    LandType findByIdNativeQuery(@Param("idLandType") Long idLandType);

    /**
     * Create by : SangNP
     * Date create: 02/02/2023
     * Description: take land type list
     * @return List<LandType>
     */
    @Query(value = "select * from sprint_1.land_type", nativeQuery = true)
    List<LandType> findAllNativeQuery();
}

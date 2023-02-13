package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.entity.post.DemandType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDemandTypeRepository extends JpaRepository<DemandType, Long> {
    @Query(value = "select * from sprint_1.demand_type as dt where dt.id_demand_type = :idDemand", nativeQuery = true)
    DemandType findByIdNativeQuery(@Param("idDemand") Long idDemand);
    /**
     * Create by: NgocLV
     * Date created: 02/02/2023
     * Function: find all demandType list
     *
     */

    @Query(value = "select * from demand_type",
            nativeQuery = true)
    List<DemandType> getAllDemandType();
}

package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.entity.post.Wards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWardsRepository extends JpaRepository<Wards, Long> {
    /**
     * Method use: findNameWardsAndIdWardsQuery to get List data of required attributes from the database of table (district)
     * @param : No
     * @return : List<Wards>
     * Author: DatTQ
     * Date create: 03/02/2023
     */
    @Query(value = "select w.id_wards, w.name_wards,w.district_id_district from sprint_1.wards as w", nativeQuery = true)
    List<Wards> findNameWardsAndIdWardsQuery();

    @Query(value = "select w.name_wards from sprint_1.wards as w where w.id_wards = :idWards", nativeQuery = true)
    String findNameByIdNativeQuery(@Param("idWards") Long idWards);
    /**
     * Create by: NgocLV
     * Date created: 02/02/2023
     * Function: find all wards list have the same district
     *
     */

    @Query(value = "select * from sprint_1.wards w where w.district_id_district = :districtSearch ",
            nativeQuery = true)
    List<Wards> findListWards(@Param("districtSearch") Long districtSearch);
}

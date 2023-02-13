package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.entity.post.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDistrictRepository extends JpaRepository<District, Long> {
    /**
     * Method use: findNameDistrictAndIdDistrictQuery to get List data of required attributes from the database of table (district)
     * @param : No
     * @return : List<District>
     * Author: DatTQ
     * Date create: 03/02/2023
     */
    @Query(value = "select d.id_district, d.name_district,d.city_id_city from sprint_1.district as d", nativeQuery = true)
    List<District> findNameDistrictAndIdDistrictQuery();

    /**
     * Method use : findNameByIdNativeQuery to get data of required attributes from the database of table (district)
     * @param : idDistrict
     * @return : nameDistrict
     * Author: DatTQ
     * Date create: 03/02/2023
     */
    @Query(value = "select d.name_district from sprint_1.district as d where d.id_district = :idDistrict", nativeQuery = true)
    String findNameByIdNativeQuery(@Param("idDistrict") Long idDistrict);
    /**
     * Create by: NgocLV
     * Date created: 02/02/2023
     * Function: find all district list have the same city
     *
     */
    @Query(value = "select * from sprint_1.district d where d.city_id_city = :citySearch ",
            nativeQuery = true)
    List<District> findListDistrict(@Param("citySearch") Long citySearch);

}

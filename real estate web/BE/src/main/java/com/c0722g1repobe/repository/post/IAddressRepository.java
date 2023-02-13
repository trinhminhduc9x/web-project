package com.c0722g1repobe.repository.post;

import com.c0722g1repobe.entity.post.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "select a.id_address from sprint_1.address as a where a.number_address = :numberAddress AND a.wards_id_wards= :idWards", nativeQuery = true)
    Long findIdByNumberAddressAndIdWardsNativeQuery(@Param("numberAddress") String numberAddress, @Param("idWards") Long idWards);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "insert into sprint_1.address " +
            "(number_address, " +
            "wards_id_wards)" +
            "VALUES (" +
            ":numberAddress," +
            ":idWards)",
            nativeQuery = true)
    void saveAddress(@Param("numberAddress") String numberAddress, @Param("idWards") Long idWards);
}

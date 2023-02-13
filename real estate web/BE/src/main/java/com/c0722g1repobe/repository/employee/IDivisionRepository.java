package com.c0722g1repobe.repository.employee;

import com.c0722g1repobe.entity.employee.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDivisionRepository extends JpaRepository<Division, Long> {
    /**
     * Create by: NhanUQ
     * Date created: 02/02/2023
     * Function: show list division
     *
     * @return list division json
     */
    @Query(value = "SELECT * FROM division", nativeQuery = true)
    List<Division> getAllDivision();
}

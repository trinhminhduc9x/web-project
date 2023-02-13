package com.c0722g1repobe.repository.customer;

import com.c0722g1repobe.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select c.id_customer from sprint_1.customer as c where c.id_customer = :idCustomer and c.flag_delete=false", nativeQuery = true)
    Long findIdByIdNativeQuery(@Param("idCustomer") Long idCustomer);
}

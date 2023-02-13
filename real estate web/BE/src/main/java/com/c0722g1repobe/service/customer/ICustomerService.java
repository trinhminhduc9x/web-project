package com.c0722g1repobe.service.customer;

import com.c0722g1repobe.dto.customer.ICustomerDtoMailAndUserName;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.dto.customer.ICustomerDto;
import com.c0722g1repobe.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICustomerService {

    /**
     * Create by: HuyNV
     * Date created : 31/01/2023
     * Function : to create customer
     *
     * @param customer
     */
    void createCustomer(Customer customer);

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : find customer by id
     *
     * @param idCustomer
     * @return
     */
    Customer findById(Long idCustomer);

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: .
     *
     * @param allSearch
     * @param pageable
     * @return
     */
    Page<ICustomerDto> searchCustomer(String allSearch, Pageable pageable);

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: .
     *
     * @param id
     * @return
     */
    Optional<Customer> findByIdCustomer(Long id);

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: .
     *
     * @param id
     */
    void confirmCustomer(Long id);

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: delete customer.
     *
     * @param id
     */
    void deleteCustomer(Long id);

    /**
     * Create by: VanNTC
     * Date created: 31/01/2023
     * Function: find customer by id
     *
     * @param idCustomer
     */
    Customer findCustomer(Long idCustomer);

    /**
     * Create by: VanNTC
     * Date created: 31/01/2023
     * Function:update customer
     *
     * @param customer
     */
    void updateCustomer(Customer customer);


    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    String ramdomCodeCustomer();
    void saveCustomer(Customer customer);

    List<ICustomerDtoMailAndUserName> findAllCheckMailCustomerAnhNameAccount();


}

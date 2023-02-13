package com.c0722g1repobe.service.customer.impl;

import com.c0722g1repobe.dto.customer.ICustomerDtoMailAndUserName;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.repository.account.IAccountRepository;
import com.c0722g1repobe.service.account.IAccountService;
import com.c0722g1repobe.dto.customer.ICustomerDto;
import com.c0722g1repobe.entity.customer.Customer;
import com.c0722g1repobe.repository.customer.ICustomerRepository;
import com.c0722g1repobe.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    /**
     * Create by: HuyNV
     * Date created : 31/01/2023
     * Function : to create customer
     *
     * @param customer
     */
    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : to create customer
     *
     * @param idCustomer
     * @return
     */
    @Override
    public Customer findById(Long idCustomer) {
        return customerRepository.findById(idCustomer).orElse(null);
    }

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: .
     *
     * @param allSearch
     * @param pageable
     * @return
     */
    @Override
    public Page<ICustomerDto> searchCustomer(String allSearch, Pageable pageable) {
        return customerRepository.searchCustomer(allSearch,pageable);
    }

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: .
     *
     * @param id
     * @return
     */
    @Override

    public Optional<Customer> findByIdCustomer(Long id) {
        return customerRepository.findByIdCustomer(id);
    }



    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: .
     *
     * @param id
     */
    @Override
    public void confirmCustomer(Long id) {
        customerRepository.confirmCustomer(id);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomer(id);
    }

    /**
     * Create by: VanNTC
     * Date created: 31/01/2023
     * Function: find customer by id
     *
     * @param idCustomer
     */
    @Override
    public Customer findCustomer(Long idCustomer) {
        return customerRepository.findCustomer(idCustomer);
    }
    /**
     * Create by: VanNTC
     * Date created: 31/01/2023
     * Function:update customer
     *
     * @param customer
     */
    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer.getNameCustomer(), customer.getEmailCustomer(), customer.getAddressCustomer(), customer.getDateOfBirth(), customer.getIdCardCustomer(), customer.getGenderCustomer(), customer.getApprovalCustomer(), customer.getPhoneCustomer1(), customer.getPhoneCustomer2(), customer.getIdCustomer());
    }


    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    @Override
    public String ramdomCodeCustomer() {
        Random generator = new Random();
        Integer codeCustomer = generator.nextInt((999 - 0) + 1) + 0;
        String codeCustomerString = "KH-" + codeCustomer.toString();
        return codeCustomerString;
    }

    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    @Override
    public List<ICustomerDtoMailAndUserName> findAllCheckMailCustomerAnhNameAccount() {
        return customerRepository.findAllCheckMailCustomerAnhNameAccount();
    }

}

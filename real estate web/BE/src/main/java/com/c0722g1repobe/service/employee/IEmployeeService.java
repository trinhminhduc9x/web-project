package com.c0722g1repobe.service.employee;

import com.c0722g1repobe.dto.employee.EmployeeInfo;
import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.employee.Employee;


import java.util.Optional;

public interface IEmployeeService {

    /**
     * Create by: NhanUQ
     * Date created: 31/01/2023
     * Function: show list employee
     *
     * @param pageable
     *
     * @return json list employee
     */
    Page<EmployeeInfo> getAllEmployee(Pageable pageable);

    /**
     * Create by: NhanUQ
     * Date created: 31/01/2023
     * Function: search employee
     *
     * @param codeSearch
     * @param nameSearch
     * @param emailSearch
     * @param nameDivisionSearch
     * @param pageable
     *
     * @return json list employee searched
     */
    Page<EmployeeInfo> searchEmployeeByCodeByNameByEmailByNameDivision(String codeSearch, String nameSearch, String emailSearch, String nameDivisionSearch, Pageable pageable);

    /**
     * Create by: NhanUQ
     * Date created: 31/01/2023
     * Function: delete employee
     *
     * @param id
     * @return boolean
     */
    boolean isDeleteEmployee(Long id);

    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: find by id to employee
     * @param id
     */
    Optional<Employee> findById(Long id);
    
    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: create to employee
     * @param employee
     */
    void saveEmployee(Employee employee);
    
    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: update to employee
     * @param id
     * @param employee
     */
    void updateEmployee(Employee employee, Long id);

    /**
     * Create by: LongPT
     * Date created : 03/02/2023
     * Function : save account
     * @param account
     */
    void saveAccount(Account account);

    /**
     * Create by: LongPT
     * Date created : 03/02/2023
     * Function : get id account
     * @param username
     */
    Account getIdAccount(String username);

    /**
     * Create by: LongPT
     * Date created : 03/02/2023
     * Function : get id account
     * @param name
     */
    Role getRoleByName(RoleName name);
}

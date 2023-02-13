package com.c0722g1repobe.service.employee.impl;

import com.c0722g1repobe.dto.employee.EmployeeInfo;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import com.c0722g1repobe.entity.employee.Employee;
import com.c0722g1repobe.repository.account.IAccountRepository;
import com.c0722g1repobe.repository.employee.IEmployeeRepository;
import com.c0722g1repobe.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IAccountRepository accountRepository;

    /**
     * Create by: NhanUQ
     * Date created: 31/01/2023
     * Function: show list employee
     *
     * @param pageable
     * @return json list employee
     */
    @Override
    public Page<EmployeeInfo> getAllEmployee(Pageable pageable) {
        return employeeRepository.getAllEmployee(pageable);
    }

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
     * @return json list employee searched
     */
    @Override
    public Page<EmployeeInfo> searchEmployeeByCodeByNameByEmailByNameDivision(
            String codeSearch,
            String nameSearch,
            String emailSearch,
            String nameDivisionSearch,
            Pageable pageable) {
        return employeeRepository.searchEmployeeByCodeByNameByEmailByNameDivision(
                codeSearch,
                nameSearch,
                emailSearch,
                nameDivisionSearch,
                pageable);
    }

    /**
     * Create by: NhanUQ
     * Date created: 31/01/2023
     * Function: delete employee
     *
     * @param id
     * @return true if delete success or false if id not exist
     */
    @Override
    public boolean isDeleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.getByIdEmployee(id);
        if (!employee.isPresent()) {
            return false;
        }
        employeeRepository.deleteEmployee(id);
        return true;
    }

    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: find by id to employee
     *
     * @param id
     */
    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.getByIdEmployee(id);
    }

    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: create to employee
     *
     * @param employee
     */
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: update to employee
     *
     * @param id
     * @param employee
     */
    @Override
    public void updateEmployee(Employee employee, Long id) {
        employeeRepository.updateEmployee(id, employee);

    }
    /**
     * Create by: LongPT
     * Date created : 03/02/2023
     * Function : save account
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        accountRepository.saveAccount(account.getUsernameAccount(), account.getEncryptPassword());
    }

    /**
     * Create by: LongPT
     * Date created : 03/02/2023
     * Function : save account
     * @param username
     */
    @Override
    public Account getIdAccount(String username) {
        return employeeRepository.getIdAccount(username);
    }

    /**
     * Create by: LongPT
     * Date created : 03/02/2023
     * Function : get role by name
     * @param name
     */
    @Override
    public Role getRoleByName(RoleName name) {
        return employeeRepository.getRoleByName(name);
    }
}

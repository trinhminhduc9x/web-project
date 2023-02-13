package com.c0722g1repobe.controller.employee;


import com.c0722g1repobe.dto.employee.EmployeeInfo;

import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import com.c0722g1repobe.service.account.impl.AccountService;
import com.c0722g1repobe.service.account.impl.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.c0722g1repobe.dto.employee.EmployeeDto;
import com.c0722g1repobe.entity.employee.Employee;
import com.c0722g1repobe.service.employee.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;

    /**
     * Create by: NhanUQ
     * Date created: 31/01/2023
     * Function: show list or search  employee
     *
     * @param codeSearch
     * @param nameSearch
     * @param emailSearch
     * @param nameDivisionSearch
     * @param pageable
     * @return HttpStatus.OK if connect to database return json list employee or HttpStatus.NOT_FOUND if list employee is empty
     */
    @GetMapping("/employee-list")
    public ResponseEntity<Page<EmployeeInfo>> getAllEmployee(@RequestParam(defaultValue = "") String codeSearch,
                                                             @RequestParam(defaultValue = "") String nameSearch,
                                                             @RequestParam(defaultValue = "") String emailSearch,
                                                             @RequestParam(defaultValue = "") String nameDivisionSearch,
                                                             @PageableDefault(size = 5) Pageable pageable) {
        Page<EmployeeInfo> employeeInfoPage;
        if (codeSearch != null && nameSearch != null && emailSearch != null && nameDivisionSearch != null) {
            employeeInfoPage = employeeService.searchEmployeeByCodeByNameByEmailByNameDivision(
                    codeSearch,
                    nameSearch,
                    emailSearch,
                    nameDivisionSearch,
                    pageable);
        } else {
            employeeInfoPage = employeeService.getAllEmployee(pageable);
        }
        if (employeeInfoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeInfoPage, HttpStatus.OK);
    }

    /**
     * Create by: NhanUQ
     * Date created: 31/01/2023
     * Function: delete employee
     *
     * @param id
     * @return HttpStatus.OK if id exist in database, delete success or HttpStatus.NOT_FOUND if id not found in database
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean flagDelete = employeeService.isDeleteEmployee(employee.get().getIdEmployee());
        if (flagDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: create to employee
     *
     * @param employeeDto
     */
    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@Valid
                                                    @RequestBody EmployeeDto employeeDto,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        Account account = new Account();
        BeanUtils.copyProperties(employeeDto, account);
        account.setEmail(employeeDto.getEmailEmployee());
        account.setEncryptPassword(passwordEncoder.encode(employeeDto.getAccount().getEncryptPassword()));
        account.setUsernameAccount(employeeDto.getAccount().getUsernameAccount());
        account.setName(employeeDto.getNameEmployee());
        Set<Role> roles = new HashSet<>();
        Role employeeRole = roleService.findByNameAccount(RoleName.EMPLOYEE).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(employeeRole);
        account.setRoles(roles);
        Account account1 = accountService.createAccount(account);
        BeanUtils.copyProperties(employeeDto, employee);
        employee.setAccount(account1);
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: update to employee
     *
     * @param id
     * @param employeeDto
     */
    @PatchMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid
                                                      @RequestBody EmployeeDto employeeDto,
                                                      @PathVariable("id") Long id,
                                                      BindingResult bindingResult) {
        if (!employeeService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeService.updateEmployee(employee, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: LongPT
     * Crated date: 31/01/2023
     * Function: get employee by id
     *
     * @param id
     */
    @GetMapping("{id}")
    public ResponseEntity<Employee> findId(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }
}

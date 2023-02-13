package com.c0722g1repobe.controller.customer;

import com.c0722g1repobe.dto.account.response.ResponseMessage;
import com.c0722g1repobe.dto.customer.CustomerDto;
import com.c0722g1repobe.dto.customer.ICustomerDto;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import com.c0722g1repobe.entity.customer.Customer;
import com.c0722g1repobe.jwt.jwt.JwtProvider;
import com.c0722g1repobe.service.account.impl.RoleService;
import com.c0722g1repobe.service.customer.ICustomerService;
import com.c0722g1repobe.service.account.impl.AccountService;
import com.c0722g1repobe.service.customer.impl.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    AccountService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * @param customerDto
     * method of using save customer
     */


    @PostMapping(value = "/signup")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerDto customerDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customer.setCodeCustomer(customerService.ramdomCodeCustomer());
        Account account = new Account();
        account.setName(customer.getNameCustomer());
        account.setUsernameAccount(customer.getAccount().getUsernameAccount());
        account.setEncryptPassword(passwordEncoder.encode(customer.getAccount().getEncryptPassword()));
        account.setEmail(customer.getEmailCustomer());
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByName(RoleName.CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(customerRole);
        account.setRoles(roles);
        accountService.save(account);
        customer.setAccount(account);
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : to create customer
     *
     * @param customerDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        Account account = new Account();
        BeanUtils.copyProperties(customerDto, account);

        customerDto.setEmailCustomer(customerDto.getEmailCustomer());
        customerDto.setCodeCustomer(customerService.ramdomCodeCustomer());
        account.setEncryptPassword(passwordEncoder.encode(customerDto.getEncryptPassword()));
        account.setUsernameAccount((customerDto.getEmailCustomer()));
        account.setEmail(customerDto.getEmailCustomer());
        account.setName(customerDto.getNameCustomer());
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByNameAccount(RoleName.CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(customerRole);
        account.setRoles(roles);
        Account account1 = accountService.createAccount(account);
        BeanUtils.copyProperties(customerDto, customer);
        customer.setAccount(account1);

        customerService.createCustomer(customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : to create customer
     *
     * @param idCustomer
     * @return
     */
    @GetMapping("detail/{idCustomer}")
    public ResponseEntity<Customer>detailCustomer(@PathVariable Long idCustomer){
        Customer customer = customerService.findById(idCustomer);
        try {
            if (customer == null || customer.isFlagDelete()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: Display Customer list.
     *
     * @param pageable
     * @param allSearch
     * @return HttpStatus.OK if result is not error or HttpStatus.NO_CONTENT if database is empty.
     */
    @GetMapping("")
    public ResponseEntity<Page<ICustomerDto>> getAllCustomerPaging(@PageableDefault(value = 5) Pageable pageable,
                                                                   @RequestParam(value = "allSearch", defaultValue = "") String allSearch) {
        Page<ICustomerDto> customerPage = customerService.searchCustomer(allSearch, pageable);
        if (customerPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerPage, HttpStatus.OK);
    }

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: Display Customer confirm.
     *
     * @param id
     * @return HttpStatus.OK if have id in database and confirm success, or HttpStatus.NO_CONTENT if id not found in database.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Customer> confirmCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.findByIdCustomer(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.confirmCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: VanNTC
     * Date created: 31/01/2023
     * Function: get Customer by idCustomer
     *
     * @param idCustomer
     * @return  HttpStatus.OK
     */
    @GetMapping("/{idCustomer}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long idCustomer) {
        Customer customer = this.customerService.findCustomer(idCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    
       /**
     * Create by: VanNTC
     * Date created: 31/01/2023
     * Function: update Customer
     *
     * @param customerDto
     * @return  HttpStatus.OK
     */
    @PatchMapping("/update-customer")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        if (customerService.findCustomer(customerDto.getIdCustomer()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customerService.updateCustomer(customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: HocHH
     * Date created: 31/01/2023
     * Function: Display Customer delete.
     *
     * @param id
     * @return HttpStatus.OK if have id in database and delete success, or HttpStatus.NO_CONTENT if id not found in database.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.findByIdCustomer(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

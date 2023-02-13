package com.c0722g1repobe.controller.account;


import com.c0722g1repobe.dto.account.AccountDto;
import com.c0722g1repobe.dto.account.request.SignInForm;
import com.c0722g1repobe.dto.account.response.JwtResponse;
import com.c0722g1repobe.dto.customer.CustomerDtoMd;
import com.c0722g1repobe.dto.customer.ICustomerDtoMailAndUserName;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import com.c0722g1repobe.entity.customer.Customer;
import com.c0722g1repobe.jwt.jwt.JwtProvider;
import com.c0722g1repobe.jwt.jwt.JwtTokenFilter;
import com.c0722g1repobe.jwt.userprincal.AccountPrinciple;
import com.c0722g1repobe.service.account.IAccountService;
import com.c0722g1repobe.service.account.impl.AccountService;
import com.c0722g1repobe.service.account.impl.RoleService;
import com.c0722g1repobe.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountRestController {

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Autowired
    private ICustomerService customerService;

    /***Created by VanNTC
     * Date created: 31/01/2023
     * Function:find account by idAccount
     * @param idAccount
     */
    @GetMapping("account/{idAccount}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long idAccount){
        Account account = this.accountService.findByIdAccount((idAccount));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /***Created by VanNTC
     * Date created: 31/01/2023
     * Function: Update new password
     //     * @param idAccount
     //     * @param emailCustomer
     * @return HttpStatus.OK
     */

    @PatchMapping("/update-password")
    public ResponseEntity<?> updatePassword(HttpServletRequest request, @Valid @RequestBody AccountDto accountDto) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        Account account;
        try {
            account = accountService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username" + username));
            boolean matches = passwordEncoder.matches(accountDto.getEncryptPassword(), account.getEncryptPassword());
            if (accountDto.getNewPassword() != null) {
                if (matches) {
                    account.setEncryptPassword(passwordEncoder.encode(accountDto.getNewPassword()));
                    accountService.updatePassword(account);
                } else {
                    return new ResponseEntity<>("Vui lòng nhập lại!", HttpStatus.NOT_IMPLEMENTED);
                }
            }
            return new ResponseEntity<>("Cập nhật thành công!", HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create by: PhuongLTH,
     * Date created: 31/01/2023,
     * Function: login
     * @param @RequestBody SignInForm signInForm
     * @return HttpStatus.OK ,if have username and password in database or HttpStatus.BAD_REQUEST if not found in database
     */
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getUsernameAccount(),
                accountPrinciple.getIdAccount(),
                accountPrinciple.getEmail()));
    }
    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<Customer> register(@Valid @RequestBody CustomerDtoMd customerDtoMD,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Customer>((Customer) bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDtoMD, customer);
        customer.setCodeCustomer(customerService.ramdomCodeCustomer());
        Account account = new Account();
        account.setName(customerDtoMD.getNameCustomer());
        account.setUsernameAccount(customerDtoMD.getUsernameAccount());
        account.setEncryptPassword(passwordEncoder.encode(customerDtoMD.getEncryptPassword()));
        account.setEmail(customerDtoMD.getEmailCustomer());
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByNameAccount(RoleName.CUSTOMER).orElse(new Role()) ;
        roles.add(customerRole);
        account.setRoles(roles);
        accountService.save(account);
        customer.setAccount(account);
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    @GetMapping("/ListMailCustomerAnhNameAccount")
    public ResponseEntity<List<ICustomerDtoMailAndUserName>> showList() {
        List<ICustomerDtoMailAndUserName> listAll = customerService.findAllCheckMailCustomerAnhNameAccount();
        if (listAll.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listAll, HttpStatus.OK);
    }

}

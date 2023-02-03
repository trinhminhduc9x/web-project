package com.codegym.dating.controller;

import com.codegym.dating.model.Account;
import com.codegym.dating.model.AccountRole;
import com.codegym.dating.model.FriendList;
import com.codegym.dating.model.composite.AccountRoleKey;
import com.codegym.dating.repository.IAccountRepository;
import com.codegym.dating.repository.IAccountRoleRepository;
import com.codegym.dating.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class TestRestController {
    @Autowired
    private IAccountRepository iAccountRepository;

    @GetMapping("/test")
    public ResponseEntity<String> listResponseEntity() {
        return new ResponseEntity<>(iAccountRepository.findById(7).get().getEmail(), HttpStatus.OK);
    }

}

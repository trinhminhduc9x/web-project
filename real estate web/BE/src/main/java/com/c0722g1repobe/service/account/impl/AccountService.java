package com.c0722g1repobe.service.account.impl;

import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.repository.account.IAccountRepository;
import com.c0722g1repobe.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : to create account
     *
     * @param account
     * @return
     */
    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    /**
     * Create by: VanNTC
     * Date created: 31/01/2023
     * Function:update account
     *
     * @param
     */
    @Override
    public Account findByIdAccount(Long idAccount) {
        return accountRepository.findByIdAccount(idAccount);
    }

    @Override
    public void updatePassword(Account account) {
        accountRepository.updatePassword(account.getName(),account.getUsernameAccount(),account.getEmail(), account.getEncryptPassword(),account.getIdAccount());
    }

    /**
     * Create by: PhuongLTH
     * Date created: 31/01/2023
     * Function:findByUsername
     *
     * @param username
     */
    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    /**
     * Create by: PhuongLTH
     * Date created: 31/01/2023
     * Function:existsByUsername
     *
     * @param username
     */
    @Override
    public Boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    /**
     * Create by: PhuongLTH
     * Date created: 31/01/2023
     * Function:existsByEmail
     *
     * @param email
     */
    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}

package com.c0722g1repobe.service.account;

import com.c0722g1repobe.entity.account.Account;


import java.util.Optional;

public interface IAccountService {

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : to create account
     *
     * @param account
     * @return
     */
    Account createAccount(Account account);

    /**
     * Create by: VanNTC
     * Date created : 01/02/2023
     * Function : update account
     *
     * @param
     * @return
     */

    Account findByIdAccount(Long idAccount);

    void updatePassword(Account account);
    /**
 * Create by: PhuongLTH
 * Date created : 01/02/2023
 * Function : findByUsername
 *
 * @param username
 * @return
 */
Optional<Account> findByUsername(String username);

/**
 * Create by: PhuongLTH
 * Date created : 01/02/2023
 * Function : existsByUsername
 *
 * @param username
 * @return
 */
Boolean existsByUsername(String username);

/**
 * Create by: PhuongLTH
 * Date created : 01/02/2023
 * Function : existsByUsername
 *
 * @param email
 * @return
 */
Boolean existsByEmail(String email);

    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    Account save(Account users);

}

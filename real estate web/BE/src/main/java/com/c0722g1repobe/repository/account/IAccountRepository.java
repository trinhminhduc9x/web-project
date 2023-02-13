package com.c0722g1repobe.repository.account;

import com.c0722g1repobe.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    /**
     * Created by VanNTC
     * Date created 31/12/2023
     * Function find account by idAccount
     *
     * @param idAccount
     * @@return account
     */

    @Query(value = "select * from account where id_account =:idAccount and flag_delete = 0", nativeQuery = true)
    Account findByIdAccount(@Param(value = "idAccount") Long idAccount);

    /**
     * Created by VanNTC
     * Date created 31/12/2023
     * Function Update password for account
     *
     * @param idAccount
     */
    @Transactional
    @Modifying
    @Query(value = "update account as a set a.name=?1, a.username_account=?2, a.email=?3, a.encrypt_password =?4 where a.id_account=?5", nativeQuery = true)
    void updatePassword(String name, String userNameAccount, String email, String encryptPassword, Long idAccount);

    /**
     * Create by LongPT
     * Date created 31/1/2023
     * Function save account
     */
    @Query(value = "insert into account(username_account, encrypt_password) values (:username, :password)", nativeQuery = true)
    public void saveAccount(@Param("username") String username, @Param("password") String password);

    /**
     * Create by: PhuongLTH,
     * Date created: 31/01/2023,
     * Function: findByUsername,existsByUsername,existsByEmail
     * @param usernameAccount,email
     * @return HttpStatus.OK if have usernameAccount and email in database or HttpStatus.NOT_FOUND if id not found in database
     */
    @Query(value = "select id_account, email, encrypt_password, name, username_account,flag_delete from account where flag_delete = false and username_account = :username_account",
            countQuery = "select id_account, email, encrypt_password, name, username_account,flag_delete from account where flag_delete = false and username_account = :username_account",
            nativeQuery = true)
    Optional<Account> findByUsername(@Param("username_account") String usernameAccount);

    /**
     * Create by: PhuongLTH,
     * Date created: 31/01/2023,
     * Function: existsByUsername
     * @param usernameAccount,email
     * @return HttpStatus.OK if have usernameAccount and email in database or HttpStatus.NOT_FOUND if id not found in database
     */
    @Query(value = "select username_account from account where username_account = :username_account",
            countQuery = "select username_account from account where username_account = :username_account",
            nativeQuery = true)
    Boolean existsByUsername(@Param("username_account")String usernameAccount);

    /**
     * Create by: PhuongLTH,
     * Date created: 31/01/2023,
     * Function: findByUsername,existsByUsername,existsByEmail
     * @param email
     * @return HttpStatus.OK if have usernameAccount and email in database or HttpStatus.NOT_FOUND if id not found in database
     */
    @Query(value = "select email from account where email = :?",
            countQuery = "select email from account where email = :?",
            nativeQuery = true)
    Boolean existsByEmail(String email);
}

package com.c0722g1repobe.repository.account;

import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    /**
     * Create by: PhuongLTH,
     * Date created: 31/01/2023,
     * Function: findByNameRole
     * @param roleName
     * @return HttpStatus.OK if have roleName in database or HttpStatus.NOT_FOUND if id not found in database
     */

    @Query(value = "select id_role as idRole,name from roles where name = :?",
            countQuery = "select id_role as idRole,name from roles where name = :?", nativeQuery = true)
    Optional<Role> findByNameRole(RoleName roleName);


    /**
     * Create by: PhuongLTH,
     * Date created: 31/01/2023,
     * Function: findAllRole
     * @param
     * @return HttpStatus.OK ,if have data in database or HttpStatus.NOT_FOUND if not found in database
     */
    @Query(value = "select id_role as idRole, name from roles where name = :?",
            countQuery = "select id_role as idRole, name from roles where name = :?", nativeQuery = true)
    List<Role> findAllRole();

    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    Optional<Role> findByName(RoleName roleName);
    List<Role> findAll();

}

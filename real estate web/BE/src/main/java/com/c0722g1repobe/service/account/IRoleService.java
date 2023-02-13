package com.c0722g1repobe.service.account;

import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    /**
     * Create by: PhuongLTH
     * Date created : 01/02/2023
     * Function : findByName
     *
     * @param roleName
     * @return
     */
    Optional<Role> findByName(RoleName roleName);

    /**
     * Create by: PhuongLTH
     * Date created : 01/02/2023
     * Function : findByName
     *
     * @param
     * @return
     */
    List<Role> findAll();

    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    Optional<Role> findByNameAccount(RoleName roleName);
}

package com.c0722g1repobe.service.account.impl;

import com.c0722g1repobe.entity.account.Role;
import com.c0722g1repobe.entity.account.RoleName;
import com.c0722g1repobe.repository.account.IRoleRepository;
import com.c0722g1repobe.service.account.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    IRoleRepository roleRepository;

    /**
     * Create by: PhuongLTH
     * Date created: 31/01/2023
     * Function:findByName
     *
     * @param roleName
     */
    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByNameRole(roleName);
    }

    /**
     * Create by: PhuongLTH
     * Date created: 31/01/2023
     * Function:findAll
     *
     * @param
     */
    @Override
    public List<Role> findAll() {
        return roleRepository.findAllRole();
    }

    /**
     * creator: Trịnh Minh Đức
     * date:31/01/2023
     * method of using save customer
     */
    @Override
    public Optional<Role> findByNameAccount(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

}

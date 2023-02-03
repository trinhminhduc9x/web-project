package com.codegym.dating.service;

import com.codegym.dating.model.TypeUser;
import com.codegym.dating.repository.ITypeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ITypeUserService {
    List<TypeUser> findAllTypeUser();

}

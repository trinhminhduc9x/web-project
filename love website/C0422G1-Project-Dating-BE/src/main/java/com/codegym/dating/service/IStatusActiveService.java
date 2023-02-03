package com.codegym.dating.service;

import com.codegym.dating.model.StatusActive;

import java.util.Optional;

public interface IStatusActiveService {
    StatusActive getStatusById(Integer id);
}

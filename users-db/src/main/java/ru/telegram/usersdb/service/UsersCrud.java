package ru.telegram.usersdb.service;

import java.util.List;
import java.util.Optional;

public interface UsersCrud<DTO,ID> {
    List<DTO> findAll();
    DTO findById(ID id);
    void save(DTO t);
    void update(DTO t, ID id);
    void  deleteById(ID id);
}

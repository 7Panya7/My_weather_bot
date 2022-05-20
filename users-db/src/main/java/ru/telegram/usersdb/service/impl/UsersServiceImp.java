package ru.telegram.usersdb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.telegram.usersdb.exception.UsersNotFoundException;
import ru.telegram.usersdb.mapper.UserMapper;
import ru.telegram.usersdb.model.dto.UsersDTO;
import ru.telegram.usersdb.repository.UsersRepository;
import ru.telegram.usersdb.service.UsersCrud;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImp implements UsersCrud<UsersDTO, Long> {
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UsersDTO> findAll() {
        return usersRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UsersDTO findById(Long aLong) {
        return userMapper.toDTO(usersRepository.findById(aLong).orElseThrow(UsersNotFoundException::new));
    }

    @Transactional
    @Override
    public void save(UsersDTO t) {
        usersRepository.save(userMapper.toEntity(t));
    }

    @Transactional
    @Override
    public void update(UsersDTO t, Long id) {
        var users = userMapper.toEntity(t);
        users.setId(id);
        usersRepository.save(users);
    }

    @Transactional
    @Override
    public void deleteById(Long aLong) {
        usersRepository.deleteById(aLong);
    }
}

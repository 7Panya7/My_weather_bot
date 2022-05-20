package ru.telegram.myweather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.telegram.myweather.feign.UsersDbFeignClient;
import ru.telegram.myweather.mapper.UserMapper;
import ru.telegram.myweather.model.dto.UsersDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {
    private final UsersDbFeignClient usersDbFeignClient;
    private final UserMapper userMapper;

    public List<UsersDTO> findAll(){
        return usersDbFeignClient.findAll();
    }

    public UsersDTO findById(Long id){
        return usersDbFeignClient.findById(id);
    }

    public String save(UsersDTO usersDTO){
        return usersDbFeignClient.save(usersDTO);
    }

    public String update(UsersDTO usersDTO, Long id){
        return usersDbFeignClient.update(usersDTO, id);
    }

    public UsersDTO deleteById(Long id){
        return usersDbFeignClient.deleteById(id);
    }
}

package ru.telegram.myweather.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.telegram.myweather.model.dto.UsersDTO;

import java.util.List;

@FeignClient(name = "user-db")
public interface UsersDbFeignClient {
    @GetMapping("/users/")
    List<UsersDTO> findAll();

    @GetMapping("/users/find-by-id/{id}")
    UsersDTO findById(@PathVariable("id") Long id);

    @PostMapping("/users/save")
    String save(@RequestBody UsersDTO usersDTO);

    @PostMapping("/users/update/{id}")
    String update(@RequestBody UsersDTO usersDTO, @PathVariable Long id);

    @DeleteMapping("/users/delete/{id}")
    UsersDTO deleteById(@PathVariable Long id);

}

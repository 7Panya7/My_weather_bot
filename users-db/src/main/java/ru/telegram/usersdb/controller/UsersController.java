package ru.telegram.usersdb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.telegram.usersdb.model.dto.UsersDTO;
import ru.telegram.usersdb.service.UsersCrud;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersCrud<UsersDTO, Long> usersService;

    @GetMapping("/")
    public ResponseEntity<List<UsersDTO>> findAll() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usersService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UsersDTO usersDTO) {
        usersService.save(usersDTO);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UsersDTO usersDTO, @PathVariable Long id) {
        usersService.update(usersDTO,id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UsersDTO> deleteById(@PathVariable Long id) {
        usersService.deleteById(id);
        return ResponseEntity.ok(null);
    }
}

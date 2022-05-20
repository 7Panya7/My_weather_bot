package ru.telegram.myweather.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegram.myweather.config.bot.TelegramBot;
import ru.telegram.myweather.model.dto.UsersDTO;
import ru.telegram.myweather.service.UserDbService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TelegramController {
    private final TelegramBot telegramBot;
    private final UserDbService userDbService;

    @PostMapping("/")
    public BotApiMethod<?> getWeather(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
    @GetMapping("/")
    public ResponseEntity<List<UsersDTO>> findAll() {
        return ResponseEntity.ok(userDbService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userDbService.findById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UsersDTO usersDTO, @PathVariable Long id) {
        userDbService.update(usersDTO,id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UsersDTO> deleteById(@PathVariable Long id) {
        userDbService.deleteById(id);
        return ResponseEntity.ok(null);
    }
}

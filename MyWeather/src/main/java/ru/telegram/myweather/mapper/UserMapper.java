package ru.telegram.myweather.mapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.myweather.model.dto.UsersDTO;

import java.time.LocalDateTime;

@Component
public class UserMapper {
   public UsersDTO toUsersDTO(Message message) {
        try {
            return UsersDTO.builder()
                    .telegramId(message.getFrom().getId())
                    .firstName(message.getFrom().getFirstName())
                    .dateTime(LocalDateTime.now())
                    .lastName(message.getFrom().getLastName())
                    .username(message.getFrom().getUserName())
                    .longitude(message.getLocation().getLongitude())
                    .latitude(message.getLocation().getLatitude())
                    .build();
        } catch (NullPointerException e) {
            return UsersDTO.builder()
                    .telegramId(message.getFrom().getId())
                    .firstName(message.getFrom().getFirstName())
                    .dateTime(LocalDateTime.now())
                    .lastName(message.getFrom().getLastName())
                    .username(message.getFrom().getUserName())
                    .build();
        }
    }
}

package ru.telegram.usersdb.model.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {

    private Long telegramId;
    @NotNull
    private String firstName;
    private LocalDateTime dateTime;
    private String lastName;
    private String username;
    private Double longitude;
    private Double latitude;

}


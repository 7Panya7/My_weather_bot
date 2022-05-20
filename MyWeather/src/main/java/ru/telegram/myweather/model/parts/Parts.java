package ru.telegram.myweather.model.parts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import ru.telegram.myweather.model.parts.timesofday.Day;
import ru.telegram.myweather.model.parts.timesofday.Evening;
import ru.telegram.myweather.model.parts.timesofday.Morning;
import ru.telegram.myweather.model.parts.timesofday.Night;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parts {
    @JsonProperty("night")
    private Night night;
    @JsonProperty("morning")
    private Morning morning;
    @JsonProperty("day")
    private Day day;
    @JsonProperty("evening")
    private Evening evening;
}
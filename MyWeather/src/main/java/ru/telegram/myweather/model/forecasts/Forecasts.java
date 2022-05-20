package ru.telegram.myweather.model.forecasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.telegram.myweather.model.parts.Parts;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Forecasts {
    String date;
    String sunrise;
    String sunset;
    @JsonProperty("moon_code")
    Integer moonCode;
    Parts parts;
}

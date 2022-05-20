package ru.telegram.myweather.model.forecasts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Fact {
    Long temp;
    @JsonProperty("feels_like")
    Long feelsLike;
    String condition;
    @JsonProperty("wind_speed")
    Long windSpeed;
    @JsonProperty("pressure_mm")
    Long pressureMm;
    Long humidity;
}

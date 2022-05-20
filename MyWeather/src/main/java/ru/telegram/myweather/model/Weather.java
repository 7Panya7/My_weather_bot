package ru.telegram.myweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.telegram.myweather.model.forecasts.Fact;
import ru.telegram.myweather.model.forecasts.Forecasts;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty("now_dt")
    LocalDateTime nowDt;
    @JsonProperty("def_pressure_mm")
    Long defPressureMM;
    Fact fact;
    @JsonProperty("forecasts")
    List<Forecasts> forecastsList;
}

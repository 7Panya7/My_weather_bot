package ru.telegram.myweather.model.parts.timesofday;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evening {
    @JsonProperty("temp_min")
    private Long tempMin;
    @JsonProperty("temp_avg")
    private Long tempAvg;
    @JsonProperty("temp_max")
    private Long tempMax;
    @JsonProperty("feels_like")
    private Long feelsLike;
    @JsonProperty("pressure_mm")
    private Long pressureMm;
    @JsonProperty("prec_prob")
    private Long precProb;
    @JsonProperty("condition")
    private String condition;
}

package ru.telegram.myweather.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ru.telegram.myweather.model.Weather;

@FeignClient(name = "yandex",url = "https://api.weather.yandex.ru/v2/forecast")
public interface YandexApiFeignClient {
    @GetMapping("")
    Weather getWeather(@RequestParam("lat") String lat, @RequestParam("lon") String lon, @RequestParam("extra") Boolean extra,
                       @RequestHeader("X-Yandex-API-Key") String yandexApiKey);

}

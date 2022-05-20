package ru.telegram.myweather.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.myweather.config.bot.BotState;
import ru.telegram.myweather.feign.YandexApiFeignClient;
import ru.telegram.myweather.model.Weather;
import ru.telegram.myweather.model.forecasts.Fact;
import ru.telegram.myweather.service.WeatherSaintPetersburgService;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:yandex.properties")
@ConfigurationProperties(prefix = "yandex")
public class WeatherSaintPetersburgServiceImpl implements WeatherSaintPetersburgService {

    private final YandexApiFeignClient yandexApiFeignClient;
    @Value("${yandex.api.key}")
    private String yandexApiKey;

    @Override
    public SendMessage handle(Message message) {
        var weather = yandexApiFeignClient.getWeather("59.9342802","30.3350986",true,yandexApiKey);
        String textWeather = getWeatherSaintPetersburgNowFromYandexApiMessage(weather.getFact(),weather);
        return new SendMessage(String.valueOf(message.getChatId()),textWeather);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SAINT_PETERSBURG;
    }

    private String getWeatherSaintPetersburgNowFromYandexApiMessage(Fact fact, Weather weather) {
        StringBuilder weatherStringBuilder = new StringBuilder();
        weatherStringBuilder.append("Погода в Санкт-петербурге сейчас:\n");
        weatherStringBuilder.append("Температура: ").append(fact.getTemp()).append("°C\n");
        weatherStringBuilder.append("Ощущаемая температура: ").append(fact.getFeelsLike()).append("°C\n");
        weatherStringBuilder.append("Погодное описание: ").append(weather.getFact().getCondition()).append("\n");
        weatherStringBuilder.append("Давление (в мм рт. ст.): ").append(fact.getPressureMm()).append("\n");
        weatherStringBuilder.append("Влажность воздуха: ").append(fact.getHumidity()).append("%\n");
        weatherStringBuilder.append("Восход Солнца: ").append(weather.getForecastsList().get(0).getSunrise()).append("\n");
        weatherStringBuilder.append("Закат Солнца: ").append(weather.getForecastsList().get(0).getSunset());
        return weatherStringBuilder.toString();
    }
}

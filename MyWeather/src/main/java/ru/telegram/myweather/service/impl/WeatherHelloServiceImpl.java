package ru.telegram.myweather.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.myweather.config.bot.BotState;
import ru.telegram.myweather.service.WeatherHelloService;

@Service
public class WeatherHelloServiceImpl implements WeatherHelloService {
    @Override
    public SendMessage handle(Message message) {
        var helloMessage = "Привет! Хочешь узнать погоду? Нажми на одну из этих кнопок:\n" +
                "Москва;\nСанкт-Петербург;\nСочи;\nХочешь добавить свою погоду?\nПришли мне свою геопозицию.\n" +
                "Для этого нажми на \"булавку\" и выбери раздел \"геопозиция\"";
        return new SendMessage(String.valueOf(message.getChatId()),helloMessage);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }
}

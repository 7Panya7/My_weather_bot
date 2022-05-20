package ru.telegram.myweather.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.myweather.config.bot.BotState;

public interface WeatherService {
    SendMessage handle(Message message);

    BotState getHandlerName();
}

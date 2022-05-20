package ru.telegram.myweather.config.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.myweather.service.WeatherMoscowService;
import ru.telegram.myweather.service.WeatherSaintPetersburgService;
import ru.telegram.myweather.service.WeatherService;
import ru.telegram.myweather.service.WeatherSochiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {

    private Map<BotState, WeatherService> messageHandlers = new HashMap<>();

    public BotStateContext(List<WeatherService> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        WeatherService weatherService = findService(currentState);
        return weatherService.handle(message);
    }

    public SendMessage processButton(BotState currentState, Long chatId, Message message) {
        WeatherService currentService = findService(currentState);
        if(currentState.equals(BotState.MOSCOW)) {
            WeatherMoscowService weatherMoscowService = (WeatherMoscowService) currentService;
            return weatherMoscowService.handle(message);
        }
        else if(currentState.equals(BotState.SAINT_PETERSBURG)) {
            WeatherSaintPetersburgService weatherSaintPetersburgService = (WeatherSaintPetersburgService) currentService;
            return weatherSaintPetersburgService.handle(message);
        }
        else if(currentState.equals(BotState.SOCHI)) {
            WeatherSochiService weatherYaroslavlService = (WeatherSochiService) currentService;
            return weatherYaroslavlService .handle(message);
        }
        else return null;
    }

    private WeatherService findService(BotState currentState) {
        if (isUsertTelegramState(currentState)) {
            return messageHandlers.get(BotState.MOSCOW);
        }
        return messageHandlers.get(currentState);
    }

    private boolean isUsertTelegramState(BotState currentState) {
        switch (currentState) {
            case MOSCOW:
                return true;
            default:
                return false;
        }
    }
}

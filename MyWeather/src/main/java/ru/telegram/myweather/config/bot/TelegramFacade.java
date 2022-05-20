package ru.telegram.myweather.config.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegram.myweather.mapper.UserMapper;
import ru.telegram.myweather.service.UserDbService;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramFacade {
    private final BotStateContext botStateContext;
    private final UserDbService userDbService;
    private final UserMapper userMapper;

    public Optional<BotApiMethod<?>> handleUpdate(Update update) {
        Message message = update.getMessage();
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}",
                    update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
        }else if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());

            Optional<SendMessage> optionalSendMessage = handleInputMessage(message);
            if (optionalSendMessage.isPresent()) {
                return Optional.of(optionalSendMessage.get());
            }
        }
        return Optional.empty();
    }

    private Optional<BotApiMethod<?>> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        Message message = buttonQuery.getMessage();
        message.getFrom().setId(message.getChatId());
        message.getFrom().setFirstName(message.getChat().getFirstName());
        message.getFrom().setLastName(message.getChat().getLastName());
        message.getFrom().setUserName(message.getChat().getUserName());

        log.info("Сохранение пользователя");
        userDbService.save(userMapper.toUsersDTO(message));
        log.info("Пользователь сохранен");

        SendMessage replyMessage;
        switch (buttonQuery.getData()) {
            case "buttonMoscow":
                //  userDataCache.getUsersCurrentBotState(userId);
                replyMessage = botStateContext.processButton(BotState.MOSCOW, chatId, message);
                return Optional.of(replyMessage);
            case "buttonPetersburg":
                replyMessage = botStateContext.processButton(BotState.SAINT_PETERSBURG, chatId,
                        message);
                return Optional.of(replyMessage);
            case "buttonSochi":
                replyMessage = botStateContext.processButton(BotState.SOCHI, chatId, message);
                return Optional.of(replyMessage);
        }

        return Optional.empty();
    }

    private Optional<SendMessage> handleInputMessage(Message message) {
        String inputMsg = message.getText();
        log.info("Сохранение пользователя");
        userDbService.save(userMapper.toUsersDTO(message));
        log.info("Пользователь сохранен");
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.START;
                break;
            case "/moscow":
            case "/Moscow":
                botState = BotState.MOSCOW;
                break;
            case "/saint_petersburg":
            case "/Saint_petersburg":
                botState = BotState.SAINT_PETERSBURG;
                break;
            case "/Sochi":
            case "/sochi":
                botState = BotState.SOCHI;
                break;
            default:
                return Optional.empty();
        }

        replyMessage = botStateContext.processInputMessage(botState,message);

        return Optional.of(replyMessage);
    }
}

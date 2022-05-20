package ru.telegram.myweather.config.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InlineKeyboardMarkupConfig {
    @Bean
    public InlineKeyboardMarkup inlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonMoscow = new InlineKeyboardButton();
        buttonMoscow.setText("Москва");
        buttonMoscow.setCallbackData("buttonMoscow");
        InlineKeyboardButton buttonSaintPetersburg = new InlineKeyboardButton();
        buttonMoscow.setText("Санкт-петербург");
        buttonMoscow.setCallbackData("buttonSaintPetersburg");
        InlineKeyboardButton buttonSochi = new InlineKeyboardButton();
        buttonMoscow.setText("Сочи");
        buttonMoscow.setCallbackData("buttonSochi");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonMoscow);
        keyboardButtonsRow1.add(buttonSaintPetersburg);
        keyboardButtonsRow1.add(buttonSochi);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}

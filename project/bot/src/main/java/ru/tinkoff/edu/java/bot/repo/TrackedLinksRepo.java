package ru.tinkoff.edu.java.bot.repo;

import java.util.List;
import java.util.Optional;

public interface TrackedLinksRepo {
    String addByChatIdAndLink(Long chatId, String link);
    List<String> findByChatId(Long chatId);
    Optional<String> deleteByChatIdAndLink(Long chatId, String link);
}

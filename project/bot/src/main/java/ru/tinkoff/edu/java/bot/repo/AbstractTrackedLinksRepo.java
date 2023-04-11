package ru.tinkoff.edu.java.bot.repo;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AbstractTrackedLinksRepo implements TrackedLinksRepo {
    private static Map<Long, List<String>> data = new HashMap<>();

    @Override
    public String addByChatIdAndLink(Long chatId, String link) {
        List<String> links = findByChatId(chatId);
        links.add(link);
        AbstractTrackedLinksRepo.data.put(chatId, links);
        return link;
    }

    @Override
    public List<String> findByChatId(Long chatId) {
        boolean hasChat = data.containsKey(chatId);
        if (hasChat) {
            return data.get(chatId);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<String> deleteByChatIdAndLink(Long chatId, String link) {

        List<String> links = findByChatId(chatId);
        boolean isRemoved = links.remove(link);
        if (isRemoved) {
            AbstractTrackedLinksRepo.data.put(chatId, links);
            return Optional.of(link);
        } else {
            return Optional.empty();
        }
    }
}

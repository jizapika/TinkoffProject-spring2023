package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.controllers.handlers.BotHandler;

@RestController
@BotHandler
public class BotController {

    @PostMapping("/updates")
    public ResponseEntity<?> update(@RequestBody LinkUpdateRequest request) {
        return ResponseEntity.ok().build();
    }
}

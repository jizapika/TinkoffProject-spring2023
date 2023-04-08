package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.controllers.handlers.ScrapperHandler;
import ru.tinkoff.edu.java.scrapper.dto.requests.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.responses.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.responses.ListLinksResponse;

@RestController
@ScrapperHandler
public class ScrapperController {

    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<?> registerChat(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/links")
    public ResponseEntity<ListLinksResponse> getAllLinks(@RequestParam Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/links")
    public ResponseEntity<LinkResponse> addLink(@RequestBody AddLinkRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/links")
    public ResponseEntity<LinkResponse> removeLink(@RequestBody RemoveLinkRequest request) {
        return ResponseEntity.ok().build();
    }
}
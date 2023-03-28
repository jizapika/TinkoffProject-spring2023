package ru.tinkoff.edu.java.scrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.handlers.ScrapperHandler;

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
    public ResponseEntity<?> getAllLinks(@RequestParam Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/links")
    public ResponseEntity<?> addLink(@RequestBody AddLinkRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/links")
    public ResponseEntity<?> removeLink(@RequestBody RemoveLinkRequest request) {
        return ResponseEntity.ok().build();
    }
}
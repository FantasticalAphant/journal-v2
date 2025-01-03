package io.github.generallyspecific.journalv2.ai;

import io.github.bucket4j.Bucket;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins}")
public class PromptController {
    private final ChatClient chatClient;

    // bucket with 6 tokens, refilling 3 tokens per minute
    private static final Bucket bucket = Bucket.builder()
            .addLimit(limit -> limit.capacity(6).refillGreedy(3, Duration.ofMinutes(1)))
            .build();

    public PromptController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/prompts")
    public ResponseEntity<PromptFormat> getPrompts(@RequestParam(required = false) String theme) {
        if (bucket.tryConsume(1)) {
            String promptText = !theme.isBlank()
                    ? "The theme is " + theme + ". Generate journaling prompts."
                    : "Generate journaling prompts.";

            PromptFormat prompts = chatClient.prompt()
                    .user(promptText)
                    .call()
                    .entity(PromptFormat.class);
            return ResponseEntity.ok(prompts);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

}

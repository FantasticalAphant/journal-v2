package io.github.generallyspecific.journalv2.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PromptController {
    private final ChatClient chatClient;

    public PromptController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/prompts")
    public PromptFormat getPrompts() {
        return chatClient.prompt()
                .user("Generate three journaling prompts.")
                .call()
                .entity(PromptFormat.class);
    }
}

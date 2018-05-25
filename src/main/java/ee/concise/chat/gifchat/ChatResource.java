package ee.concise.chat.gifchat;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatResource {

    private final ChatRepository chatRepository;

    public ChatResource(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping
    public List<ChatLine> findAll() {
        return chatRepository.findAll();
    }

    @PostMapping
    public ChatLine create(@RequestBody ChatLine chatLine) {
        return chatRepository.save(chatLine);
    }
}

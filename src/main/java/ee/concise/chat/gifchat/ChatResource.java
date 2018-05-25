package ee.concise.chat.gifchat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ChatLine create(ChatLine chatLine) {
        return chatRepository.save(chatLine);
    }
}

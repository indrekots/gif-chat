package ee.concise.chat.gifchat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatResource {

    private final ChatRepository chatRepository;

    public ChatResource(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping
    public List<ChatLine> findAll() {
        return chatRepository.findAll();
    }
}

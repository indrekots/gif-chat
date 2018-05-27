package ee.concise.chat.gifchat;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatResource {

    private final ChatRepository chatRepository;
    private final GiphyClient giphyClient;

    public ChatResource(ChatRepository chatRepository, GiphyClient giphyClient) {
        this.chatRepository = chatRepository;
        this.giphyClient = giphyClient;
    }

    @GetMapping
    public List<ChatLine> findAll() {
        return chatRepository.findAll();
    }

    @PostMapping
    public ChatLine create(@RequestBody ChatLine chatLine) {
        String gifUrl = giphyClient.fetchGifUrl(chatLine.getKeyword());
        chatLine.setGifUrl(gifUrl);
        return chatRepository.save(chatLine);
    }
}

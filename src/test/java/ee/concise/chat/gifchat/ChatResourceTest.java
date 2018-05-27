package ee.concise.chat.gifchat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Chat resource")
class ChatResourceTest {

    private MockMvc mvc;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private GiphyClient giphyClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ChatResource chatResource = new ChatResource(chatRepository, giphyClient);
        mvc = MockMvcBuilders.standaloneSetup(chatResource).build();
    }

    @Test
    @DisplayName("displays all chat lines")
    void findAll() throws Exception {
        when(chatRepository.findAll()).thenReturn(chatLines());
        mvc.perform(get("/api/chat"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    private List<ChatLine> chatLines() {
        ChatLine line1 = new ChatLine(1L, "wow", "Indrek", "");
        ChatLine line2 = new ChatLine(2L, "nice", "Anon", "");

        return Arrays.asList(line1, line2);
    }

    @Test
    @DisplayName("adds new chat line")
    void create() throws Exception {
        String payload = "{\n" +
            "    \"author\": \"Indrek\",\n" +
            "    \"keyword\": \"phew\"\n" +
            "}";
        when(chatRepository.save(any(ChatLine.class))).thenReturn(new ChatLine(1L, "phew", "Indrek", ""));

        mvc.perform(post("/api/chat")
            .content(payload)
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.author", is("Indrek")))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.keyword", is("phew")));

        verify(chatRepository).save(any(ChatLine.class));
    }
}
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Chat resource")
class ChatResourceTest {

    private MockMvc mvc;

    @Mock
    private ChatRepository chatRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ChatResource chatResource = new ChatResource(chatRepository);
        mvc = MockMvcBuilders.standaloneSetup(chatResource).build();
    }

    @Test
    @DisplayName("displays all chat lines")
    void findAll() throws Exception {
        when(chatRepository.findAll()).thenReturn(chatLines());
        mvc.perform(get("/chat"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    private List<ChatLine> chatLines() {
        ChatLine line1 = new ChatLine(1L, "wow", "Indrek");
        ChatLine line2 = new ChatLine(2L, "nice", "Anon");

        return Arrays.asList(line1, line2);
    }
}
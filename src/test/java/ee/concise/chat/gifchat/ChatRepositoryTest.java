package ee.concise.chat.gifchat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Chat repository")
class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @AfterEach
    void tearDown() {
        chatRepository.deleteAll();
    }

    @Test
    @DisplayName("should be able to add new chat lines to the database")
    void add() {
        ChatLine line = new ChatLine();
        line.setAuthor("Indrek");
        line.setKeyword("wow");

        chatRepository.save(line);

        assertEquals(1, chatRepository.count());
    }

    @Test
    @DisplayName("should find chat line with id of 1")
    void findById() {
        ChatLine line = new ChatLine();
        line.setAuthor("Indrek");
        line.setKeyword("wow");

        chatRepository.save(line);

        Optional<ChatLine> chatLine = chatRepository.findById(1L);
        assertTrue(chatLine.isPresent());
        chatLine.ifPresent(it -> {
            assertThat(it.getAuthor(), is("Indrek"));
            assertThat(it.getKeyword(), is("wow"));
        });
    }
}

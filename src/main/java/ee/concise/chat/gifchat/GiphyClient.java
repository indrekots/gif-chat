package ee.concise.chat.gifchat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class GiphyClient {

    @Value("${apiKey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GiphyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchGifUrl(String keyword) {
        String response = restTemplate.getForObject(giphyUrl(apiKey, keyword), String.class);
        return getGifUrl(response);
    }

    private String giphyUrl(String apiKey, String keyword) {
        return String.format("https://api.giphy.com/v1/gifs/search?api_key=%s&q=%s&limit=1&offset=0&rating=G&lang=en",
            apiKey, keyword);
    }

    private String getGifUrl(String response) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(response);
            return jsonNode.get("data").get(0).get("images").get("original").get("url").asText();
        } catch (IOException e) {
            return null;
        }
    }
}

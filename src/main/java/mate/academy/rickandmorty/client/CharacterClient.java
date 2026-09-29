package mate.academy.rickandmorty.client;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterPage;
import mate.academy.rickandmorty.dto.CharacterPage.ExternalCharacter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CharacterClient {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CharacterClient(RestTemplateBuilder builder,
            @Value("${rick-and-morty.api-url}") String apiUrl) {
        this.restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(30))
                .build();
        this.apiUrl = apiUrl;
    }

    public List<ExternalCharacter> getAllCharacters() {
        List<ExternalCharacter> characters = new ArrayList<>();
        String nextPage = apiUrl;
        while (nextPage != null) {
            CharacterPage page = restTemplate.getForObject(nextPage, CharacterPage.class);
            if (page == null || page.info() == null || page.results() == null) {
                throw new IllegalStateException("Invalid character API response: " + nextPage);
            }
            characters.addAll(page.results());
            nextPage = page.info().next();
        }
        return characters;
    }
}

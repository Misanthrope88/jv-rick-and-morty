package mate.academy.rickandmorty.config;

import jakarta.annotation.PostConstruct;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
public class CharacterDataInitializer {
    private final CharacterClient characterClient;
    private final CharacterService characterService;

    public CharacterDataInitializer(CharacterClient characterClient,
            CharacterService characterService) {
        this.characterClient = characterClient;
        this.characterService = characterService;
    }

    @PostConstruct
    public void initialize() {
        characterService.saveCharacters(characterClient.getAllCharacters());
    }
}

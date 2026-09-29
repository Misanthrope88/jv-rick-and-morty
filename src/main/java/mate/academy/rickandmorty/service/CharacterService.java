package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.CharacterPage.ExternalCharacter;
import mate.academy.rickandmorty.dto.CharacterResponse;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public CharacterService(CharacterRepository characterRepository,
            CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    public Optional<CharacterResponse> getRandom() {
        return characterRepository.findRandom().map(characterMapper::toResponse);
    }

    public List<CharacterResponse> searchByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCaseOrderByIdAsc(name).stream()
                .map(characterMapper::toResponse)
                .toList();
    }

    @Transactional
    public void saveCharacters(List<ExternalCharacter> externalCharacters) {
        Map<String, Character> existingCharacters = characterRepository.findAll().stream()
                .collect(Collectors.toMap(Character::getExternalId, Function.identity()));
        List<Character> characters = new ArrayList<>();
        for (ExternalCharacter external : externalCharacters) {
            Character character = existingCharacters.computeIfAbsent(
                    external.id().toString(), Character::new);
            character.setName(external.name());
            character.setStatus(external.status());
            character.setGender(external.gender());
            characters.add(character);
        }
        characterRepository.saveAll(characters);
    }
}

package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponse;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/characters")
@Tag(name = "Characters", description = "Rick and Morty characters from the local database")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/random")
    @Operation(summary = "Get a random character")
    @ApiResponse(responseCode = "200", description = "A randomly selected character")
    @ApiResponse(responseCode = "404", description = "The local database is empty",
            content = @Content)
    public CharacterResponse getRandom() {
        return characterService.getRandom().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No characters found"));
    }

    @GetMapping("/search")
    @Operation(summary = "Find all characters whose names contain the search string",
            description = "Case-insensitive substring search. An empty string matches all names.")
    @ApiResponse(responseCode = "200", description = "Matching characters, or an empty list")
    @ApiResponse(responseCode = "400", description = "The name parameter is missing",
            content = @Content)
    public List<CharacterResponse> search(
            @Parameter(description = "Part of a character name", example = "rick", required = true)
            @RequestParam String name) {
        return characterService.searchByName(name);
    }
}

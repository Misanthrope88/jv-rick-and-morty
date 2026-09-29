package mate.academy.rickandmorty.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CharacterResponse(
        @Schema(description = "Identifier in the local database", example = "1") Long id,
        @Schema(description = "Original identifier from the public API", example = "1")
        String externalId,
        @Schema(example = "Rick Sanchez") String name,
        @Schema(example = "Alive") String status,
        @Schema(example = "Male") String gender) {
}

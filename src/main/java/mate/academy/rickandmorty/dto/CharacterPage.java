package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CharacterPage(Info info, List<ExternalCharacter> results) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Info(String next) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ExternalCharacter(Long id, String name, String status, String gender) {
    }
}

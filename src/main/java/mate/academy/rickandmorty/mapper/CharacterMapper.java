package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterResponse;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CharacterMapper {
    CharacterResponse toResponse(Character character);
}

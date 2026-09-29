package mate.academy.rickandmorty;

import static org.mockito.Mockito.verify;

import mate.academy.rickandmorty.client.CharacterClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ApplicationTests {
    @MockBean
    private CharacterClient characterClient;

    @Test
    void contextLoads() {
        verify(characterClient).getAllCharacters();
    }
}

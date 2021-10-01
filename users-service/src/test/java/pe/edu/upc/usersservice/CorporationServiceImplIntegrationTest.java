package pe.edu.upc.usersservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.usersservice.entity.Corporation;
import pe.edu.upc.usersservice.exceptions.ResourceNotFoundException;
import pe.edu.upc.usersservice.repository.SubscriptionRepository;
import pe.edu.upc.usersservice.repository.CorporationRepository;
import pe.edu.upc.usersservice.service.CorporationService;
import pe.edu.upc.usersservice.service.impls.CorporationServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CorporationServiceImplIntegrationTest {
    @MockBean
    private CorporationRepository corporationRepository;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CorporationService corporationService;

    @TestConfiguration
    static class CorporationImplTestConfiguration {
        @Bean
        public CorporationService corporationServiceService(){
            return new CorporationServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetCorporationById With Valid Id Then Returns Corporation")
    public void whenGetCorporationByIdThenReturnsCorporation(){
        //Arrange
        Long id = 1L;
        String name = "Prueba1";
        Corporation user = new Corporation();
        user.setId(1L);
        user.setName(name);

        when(corporationRepository.findById(id)).thenReturn(Optional.of(user));

        //Act
        Corporation foundCorporation = corporationService.findById(id);
        //Assert
        assertThat(foundCorporation.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When GetCorporationById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetCorporationByIdThenReturnsNull(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(corporationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Corporation", "Id", id);

        //Act
        Throwable exception = catchThrowable(()->{
            Corporation foundCorporation = corporationService.findById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}

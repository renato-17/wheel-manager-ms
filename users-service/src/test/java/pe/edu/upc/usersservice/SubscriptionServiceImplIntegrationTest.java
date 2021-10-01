package pe.edu.upc.usersservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.usersservice.entity.Subscription;
import pe.edu.upc.usersservice.exceptions.ResourceNotFoundException;
import pe.edu.upc.usersservice.repository.SubscriptionRepository;
import pe.edu.upc.usersservice.repository.SubscriptionRepository;
import pe.edu.upc.usersservice.repository.SubscriptionTypeRepository;
import pe.edu.upc.usersservice.service.SubscriptionService;
import pe.edu.upc.usersservice.service.impls.SubscriptionServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SubscriptionServiceImplIntegrationTest {
    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @TestConfiguration
    static class SubscriptionImplTestConfiguration {
        @Bean
        public SubscriptionService subscriptionServiceService(){
            return new SubscriptionServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetSubscriptionById With Valid Id Then Returns Subscription")
    public void whenGetSubscriptionByIdThenReturnsSubscription(){
        //Arrange
        Long id = 1L;
        String name = "Prueba1";
        Subscription user = new Subscription();
        user.setId(1L);
        user.setName(name);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(user));

        //Act
        Subscription foundSubscription = subscriptionService.findById(id);
        //Assert
        assertThat(foundSubscription.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When GetSubscriptionById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetSubscriptionByIdThenReturnsNull(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(subscriptionRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Subscription", "Id", id);

        //Act
        Throwable exception = catchThrowable(()->{
            Subscription foundSubscription = subscriptionService.findById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}

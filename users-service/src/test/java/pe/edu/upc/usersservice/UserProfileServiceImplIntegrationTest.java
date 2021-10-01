package pe.edu.upc.usersservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.usersservice.entity.UserProfile;
import pe.edu.upc.usersservice.exceptions.ResourceNotFoundException;
import pe.edu.upc.usersservice.repository.SubscriptionRepository;
import pe.edu.upc.usersservice.repository.UserProfileRepository;
import pe.edu.upc.usersservice.service.UserProfileService;
import pe.edu.upc.usersservice.service.impls.UserProfileServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserProfileServiceImplIntegrationTest {
    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserProfileService userProfileService;

    @TestConfiguration
    static class UserProfileImplTestConfiguration {
        @Bean
        public UserProfileService userProfileServiceService(){
            return new UserProfileServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetUserProfileById With Valid Id Then Returns User Profile")
    public void whenGetUserProfileByIdThenReturnsUserProfile(){
        //Arrange
        Long id = 1L;
        String name = "Prueba1";
        UserProfile user = new UserProfile();
        user.setId(1L);
        user.setName(name);

        when(userProfileRepository.findById(id)).thenReturn(Optional.of(user));

        //Act
        UserProfile foundUserProfile = userProfileService.findById(id);
        //Assert
        assertThat(foundUserProfile.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When GetUserProfileById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetUserProfileByIdThenReturnsNull(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(userProfileRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "UserProfile", "Id", id);

        //Act
        Throwable exception = catchThrowable(()->{
            UserProfile foundUserProfile = userProfileService.findById(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}

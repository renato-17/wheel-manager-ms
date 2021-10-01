package pe.edu.upc.usersservice.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.upc.usersservice.entity.UserProfile;
import pe.edu.upc.usersservice.exceptions.ResourceNotFoundException;
import pe.edu.upc.usersservice.repository.SubscriptionRepository;
import pe.edu.upc.usersservice.repository.UserProfileRepository;
import pe.edu.upc.usersservice.service.UserProfileService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<UserProfile> findAll(Pageable pageable) {
        return userProfileRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public UserProfile findById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("UserProfile","Id",id));
    }

    @Transactional()
    @Override
    public UserProfile update(Long id,UserProfile newEntity) {
        UserProfile user = userProfileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("UserProfile","Id",id));

        user.setBirthdate(newEntity.getBirthdate());
        user.setGender(newEntity.getGender());
        user.setLastName(newEntity.getLastName());
        user.setUsername(newEntity.getUsername());
        user.setName(newEntity.getName());
        user.setEmail(newEntity.getEmail());
        user.setPassword(newEntity.getPassword());

        return userProfileRepository.save(user);
    }

    @Transactional()
    @Override
    public ResponseEntity<?> delete(Long id) {
        UserProfile user = userProfileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("UserProfile","Id",id));

        userProfileRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Transactional()
    @Override
    public UserProfile save(UserProfile userProfile, Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).map(subscription -> {
            userProfile.setSubscription(subscription);
            return userProfileRepository.save(userProfile);
        }).orElseThrow(()->new ResourceNotFoundException("Subscription","Id",subscriptionId));

    }
}

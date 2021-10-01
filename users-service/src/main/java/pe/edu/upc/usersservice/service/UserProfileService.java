package pe.edu.upc.usersservice.service;

import pe.edu.upc.usersservice.entity.UserProfile;

import java.util.Optional;

public interface UserProfileService extends CrudService<UserProfile,Long> {
    UserProfile save(UserProfile userProfile, Long subscriptionId);
}

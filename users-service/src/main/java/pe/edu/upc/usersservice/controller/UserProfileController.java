package pe.edu.upc.usersservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.usersservice.entity.UserProfile;
import pe.edu.upc.usersservice.service.UserProfileService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/user-profiles")
    public Page<UserProfile> getAllUserProfiles(Pageable pageable){
        return userProfileService.findAll(pageable);
    }

    @GetMapping("/user-profiles/{id}")
    public UserProfile fetchById(@PathVariable(name = "id") Long id){
        return userProfileService.findById(id);
    }

    @PostMapping("/subscriptions/{subscriptionId}/user-profiles")
    public UserProfile postUserProfile(@Valid @RequestBody UserProfile user, @PathVariable(name = "subscriptionId") Long subscriptionId){
        return userProfileService.save(user,subscriptionId);
    }
}

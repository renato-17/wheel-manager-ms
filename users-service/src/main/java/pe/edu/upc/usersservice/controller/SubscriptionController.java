package pe.edu.upc.usersservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.usersservice.entity.Subscription;
import pe.edu.upc.usersservice.service.SubscriptionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/subscriptions")
    public Page<Subscription> getAllSubscriptions(Pageable pageable){
        return subscriptionService.findAll(pageable);
    }

    @GetMapping("/subscriptions/{id}")
    public Subscription fetchById(@PathVariable(name = "id") Long id){
        return subscriptionService.findById(id);
    }


    @PostMapping("/subscriptions-types/{subscriptionTypeId}/subscriptions")
    public Subscription postSubscription(@Valid @RequestBody Subscription subscription, @PathVariable(name = "subscriptionTypeId") Long subscriptionTypeId){
        return subscriptionService.save(subscription,subscriptionTypeId);
    }
}

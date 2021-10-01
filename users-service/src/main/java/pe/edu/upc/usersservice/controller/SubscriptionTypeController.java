package pe.edu.upc.usersservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.usersservice.entity.SubscriptionType;
import pe.edu.upc.usersservice.service.SubscriptionTypeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SubscriptionTypeController {
    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    @GetMapping("/subscription-types")
    public Page<SubscriptionType> getAllSubscriptionTypes(Pageable pageable){
        return subscriptionTypeService.findAll(pageable);
    }

    @GetMapping("/subscription-types/{id}")
    public SubscriptionType fetchById(@PathVariable(name = "id") Long id){
        return subscriptionTypeService.findById(id);
    }

    @PostMapping("/subscription-types")
    public SubscriptionType postSubscriptionType(@Valid @RequestBody SubscriptionType subscriptionType){
        return subscriptionTypeService.save(subscriptionType);
    }
}

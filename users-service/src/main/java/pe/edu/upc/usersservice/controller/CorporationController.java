package pe.edu.upc.usersservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.usersservice.entity.Corporation;
import pe.edu.upc.usersservice.service.CorporationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CorporationController {
    @Autowired
    private CorporationService corporationService;

    @GetMapping("/corporations")
    public Page<Corporation> getAllCorporations(Pageable pageable){
        return corporationService.findAll(pageable);
    }

    @GetMapping("/corporations/{id}")
    public Corporation fetchById(@PathVariable(name = "id") Long id){
        return corporationService.findById(id);
    }


    @PostMapping("/subscriptions/{subscriptionId}/corporations")
    public Corporation postCorporation(@Valid @RequestBody Corporation user, @PathVariable(name = "subscriptionId") Long subscriptionId){
        return corporationService.save(user,subscriptionId);
    }
}

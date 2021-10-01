package pe.edu.upc.usersservice.service.impls;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.usersservice.entity.Corporation;
import pe.edu.upc.usersservice.exceptions.ResourceNotFoundException;
import pe.edu.upc.usersservice.repository.CorporationRepository;
import pe.edu.upc.usersservice.repository.SubscriptionRepository;
import pe.edu.upc.usersservice.service.CorporationService;

@Service
public class CorporationServiceImpl implements CorporationService {
    @Autowired
    private CorporationRepository corporationRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Transactional()
    @Override
    public Corporation save(Corporation corporation, Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).map(subscription -> {
            corporation.setSubscription(subscription);
            return corporationRepository.save(corporation);
        }).orElseThrow(()->new ResourceNotFoundException("Subscription","Id",subscriptionId));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Corporation> findAll(Pageable pageable) {
        return corporationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Corporation findById(Long id) {
        return corporationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Corporation","Id",id));
    }

    @Transactional()
    @Override
    public Corporation update(Long id, Corporation newEntity) {
        Corporation corporation = corporationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Corporation","Id",id));

        corporation.setUsername(newEntity.getUsername());
        corporation.setName(newEntity.getName());
        corporation.setEmail(newEntity.getEmail());
        corporation.setPassword(newEntity.getPassword());
        corporation.setAddress(newEntity.getAddress());
        corporation.setRuc(newEntity.getRuc());
        corporation.setPhone(newEntity.getPhone());

        return corporationRepository.save(corporation);
    }

    @Transactional()
    @Override
    public ResponseEntity<?> delete(Long id) {
        Corporation corporation = corporationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Corporation","Id",id));
        corporationRepository.delete(corporation);
        return ResponseEntity.ok().build();
    }
}

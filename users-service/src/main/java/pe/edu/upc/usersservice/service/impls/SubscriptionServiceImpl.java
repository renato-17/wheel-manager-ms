package pe.edu.upc.usersservice.service.impls;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.usersservice.entity.Subscription;
import pe.edu.upc.usersservice.exceptions.ResourceNotFoundException;
import pe.edu.upc.usersservice.repository.SubscriptionRepository;
import pe.edu.upc.usersservice.repository.SubscriptionTypeRepository;
import pe.edu.upc.usersservice.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Subscription> findAll(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription","Id",id));
    }

    @Transactional()
    @Override
    public Subscription update(Long id, Subscription newEntity) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription","Id",id));
        subscription.setName(newEntity.getName());
        subscription.setDateStart(newEntity.getDateStart());
        subscription.setStatus(newEntity.getStatus());
        return subscriptionRepository.save(subscription);
    }

    @Transactional()
    @Override
    public ResponseEntity<?> delete(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription","Id",id));
        subscriptionRepository.save(subscription);
        return ResponseEntity.ok().build();
    }

    @Transactional()
    @Override
    public Subscription save(Subscription subscription, Long subscriptionTypeId) {
        return subscriptionTypeRepository.findById(subscriptionTypeId).map(subscriptionType -> {
            subscription.setSubscriptionType(subscriptionType);
            return subscriptionRepository.save(subscription);
        }).orElseThrow(()->new ResourceNotFoundException("Subscription Type","Id",subscriptionTypeId));

    }

    @Transactional(readOnly = true)
    @Override
    public Subscription findByIdAndSubscriptionTypeId(Long id, Long subscriptionTypeId) {
        return subscriptionRepository.findByIdAndSubscriptionTypeId(id, subscriptionTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription or Subscription Type not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Subscription> findAllBySubscriptionTypeId(Long subscriptionTypeId, Pageable pageable) {
        return subscriptionRepository.findBySubscriptionTypeId(subscriptionTypeId,pageable);
    }
}

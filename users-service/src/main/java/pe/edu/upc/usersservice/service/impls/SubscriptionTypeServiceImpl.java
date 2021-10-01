package pe.edu.upc.usersservice.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.upc.usersservice.entity.SubscriptionType;
import pe.edu.upc.usersservice.exceptions.ResourceNotFoundException;
import pe.edu.upc.usersservice.repository.SubscriptionTypeRepository;
import pe.edu.upc.usersservice.service.SubscriptionTypeService;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {
    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public Page<SubscriptionType> findAll(Pageable pageable) {
        return subscriptionTypeRepository.findAll(pageable);
    }

    @Override
    public SubscriptionType findById(Long id) {
        return subscriptionTypeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription Type","Id",id));
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionType newEntity) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription Type","Id",id));

        subscriptionType.setName(newEntity.getName());
        subscriptionType.setPrice(newEntity.getPrice());
        subscriptionType.setDescription(newEntity.getDescription());
        subscriptionType.setStatus(newEntity.getStatus());
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription Type","Id",id));
        subscriptionTypeRepository.delete(subscriptionType);
        return ResponseEntity.ok().build();
    }

    @Override
    public SubscriptionType save(SubscriptionType subscriptionType) {
        return subscriptionTypeRepository.save(subscriptionType);
    }
}

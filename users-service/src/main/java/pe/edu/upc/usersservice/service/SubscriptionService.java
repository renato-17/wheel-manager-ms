package pe.edu.upc.usersservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.upc.usersservice.entity.Subscription;

import java.util.Optional;

public interface SubscriptionService extends CrudService<Subscription,Long> {
    Subscription save(Subscription subscription, Long subscriptionTypeId);
    Subscription findByIdAndSubscriptionTypeId(Long id, Long subscriptionTypeId);
    Page<Subscription> findAllBySubscriptionTypeId(Long subscriptionTypeId, Pageable pageable);
}

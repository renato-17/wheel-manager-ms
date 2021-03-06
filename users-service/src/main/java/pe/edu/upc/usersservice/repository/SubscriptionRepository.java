package pe.edu.upc.usersservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.usersservice.entity.Subscription;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    Page<Subscription> findBySubscriptionTypeId(Long adId, Pageable pageable);
    Optional<Subscription> findByIdAndSubscriptionTypeId(Long id, Long subscriptionTypeId);
}

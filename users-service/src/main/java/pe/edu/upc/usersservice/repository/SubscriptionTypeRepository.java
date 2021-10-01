package pe.edu.upc.usersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.usersservice.entity.SubscriptionType;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType,Long> {
}

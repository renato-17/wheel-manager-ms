package pe.edu.upc.usersservice.service;

import pe.edu.upc.usersservice.entity.SubscriptionType;
import java.util.Optional;

public interface SubscriptionTypeService extends CrudService<SubscriptionType,Long> {
    SubscriptionType save(SubscriptionType subscriptionType);
}

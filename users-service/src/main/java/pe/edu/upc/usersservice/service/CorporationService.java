package pe.edu.upc.usersservice.service;

import pe.edu.upc.usersservice.entity.Corporation;

import java.util.Optional;

public interface CorporationService extends CrudService<Corporation,Long> {
    Corporation save(Corporation corporation, Long subscriptionId);
}

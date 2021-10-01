package pe.edu.upc.usersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.usersservice.entity.Corporation;

public interface CorporationRepository extends JpaRepository<Corporation,Long> {
}

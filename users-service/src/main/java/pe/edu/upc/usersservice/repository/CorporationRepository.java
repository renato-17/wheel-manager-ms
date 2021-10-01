package pe.edu.upc.usersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.usersservice.entity.Corporation;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation,Long> {
}

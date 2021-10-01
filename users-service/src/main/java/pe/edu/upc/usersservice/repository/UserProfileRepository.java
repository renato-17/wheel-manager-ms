package pe.edu.upc.usersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.usersservice.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}

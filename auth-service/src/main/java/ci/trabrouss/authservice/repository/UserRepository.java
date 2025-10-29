package ci.trabrouss.authservice.repository;

import ci.trabrouss.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

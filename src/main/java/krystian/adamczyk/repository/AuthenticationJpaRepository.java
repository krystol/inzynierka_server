package krystian.adamczyk.repository;

import krystian.adamczyk.model.AuthenticationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationJpaRepository extends JpaRepository<AuthenticationRequest, Integer> {
}

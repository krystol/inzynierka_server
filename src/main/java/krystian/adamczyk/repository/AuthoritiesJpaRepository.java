package krystian.adamczyk.repository;

import krystian.adamczyk.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesJpaRepository  extends JpaRepository<Authorities, Integer> {

}

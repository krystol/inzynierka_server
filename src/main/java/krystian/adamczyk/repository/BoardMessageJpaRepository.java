package krystian.adamczyk.repository;

import krystian.adamczyk.model.BoardMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMessageJpaRepository extends JpaRepository<BoardMessage, Integer> {
}

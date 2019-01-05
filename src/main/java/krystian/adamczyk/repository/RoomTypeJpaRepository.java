package krystian.adamczyk.repository;

import krystian.adamczyk.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeJpaRepository extends JpaRepository<RoomType, Integer> {
}

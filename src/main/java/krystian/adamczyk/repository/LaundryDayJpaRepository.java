package krystian.adamczyk.repository;

import krystian.adamczyk.model.LaundryDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface LaundryDayJpaRepository extends JpaRepository<LaundryDay, Integer> {

  Optional<LaundryDay> findByLaundryIdAndDate(int laundry, LocalDate date);

}

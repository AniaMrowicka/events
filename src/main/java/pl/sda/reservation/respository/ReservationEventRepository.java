package pl.sda.reservation.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.reservation.model.ReservationEvent;

public interface ReservationEventRepository extends JpaRepository<ReservationEvent, Long> {
}

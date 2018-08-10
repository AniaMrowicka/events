package pl.sda.reservation.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.reservation.model.AppUser;
import pl.sda.reservation.model.Reservation;

import javax.xml.ws.soap.Addressing;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {



}

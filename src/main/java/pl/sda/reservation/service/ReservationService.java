package pl.sda.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.reservation.model.CreateReservationDTO;
import pl.sda.reservation.model.Reservation;
import pl.sda.reservation.model.ReservationEvent;
import pl.sda.reservation.model.ReservationStatus;
import pl.sda.reservation.respository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationEventService reservationEventService;

    public Reservation saveReservation(Reservation reservation){
       return reservationRepository.save(reservation);
    }
    public Reservation addReservation(CreateReservationDTO createReservationDTO){
        Optional<ReservationEvent>event=reservationEventService.find(createReservationDTO.getEventId());

        if (event.isPresent()){
            ReservationEvent event1= event.get();
            Reservation reservation=new Reservation(null, createReservationDTO.getParticipantName(), event1, ReservationStatus.UNCONFIRMED);
           return saveReservation(reservation);
        }
        return null;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }
    public void removerReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Optional<Reservation> find(Long id) {
        return reservationRepository.findById(id);
    }


}

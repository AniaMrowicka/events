package pl.sda.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.reservation.model.ReservationEvent;
import pl.sda.reservation.respository.ReservationEventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationEventService {


    @Autowired
    private ReservationEventRepository reservationEventRepository;



    public void saveReservation (ReservationEvent reservationEvent){
         reservationEventRepository.save(reservationEvent);
    }
    public List<ReservationEvent> listsEvents(){
        return reservationEventRepository.findAll();
    }


}

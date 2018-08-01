package pl.sda.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.reservation.model.Person;
import pl.sda.reservation.model.ReservationEvent;
import pl.sda.reservation.service.ReservationEventService;

@Controller
@RestController
public class ReservationEventController {

    @Autowired
    private ReservationEventService reservationEventService;

    @GetMapping(path="/addReservation")
    public String addEvent(Model model) {
        ReservationEvent reservationEvent = new ReservationEvent();
        model.addAttribute("reservationEvent", reservationEvent);
        return "addReservation";
    }

    @PostMapping(path="/addReservation")
    public String showEvent(Model model, ReservationEvent reservationEvent) {
       reservationEventService.saveReservation(reservationEvent);
        return "redirect:/";
    }


}

package pl.sda.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.reservation.model.ReservationEvent;
import pl.sda.reservation.service.ReservationEventService;

import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private ReservationEventService reservationEventService;


    @GetMapping(path = "/list")
    public String list(Model model){
        List<ReservationEvent> eventList= reservationEventService.getAllEvents();
        model.addAttribute("eventList", eventList);
        return "eventList";
    }
    @GetMapping(path="/addEvent")
    public String addEvent(Model model) {
        ReservationEvent reservationEvent = new ReservationEvent();
        model.addAttribute("reservationEvent", reservationEvent);
        return "addEvent";
    }

    @PostMapping(path="/addEvent")
    public String showEvent(Model model, ReservationEvent reservationEvent) {
       reservationEventService.saveReservation(reservationEvent);
        return "redirect:/list";
    }
    @GetMapping(path = "/remove/{id}")
    public String remove(@PathVariable(name="id") Long id){
        reservationEventService.removeEvent(id);
        return "redirect:/list";
    }
    @GetMapping(path = "/details/{id}")
    public String details(Model model, @PathVariable(name="id")Long id){
        Optional<ReservationEvent> eventOptional= reservationEventService.find(id);
        if (eventOptional.isPresent()){
            model.addAttribute("event", eventOptional.get());
            return "eventDetails";
        }
        return "error";
    }


}

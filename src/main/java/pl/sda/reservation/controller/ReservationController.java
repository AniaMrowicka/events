package pl.sda.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.reservation.model.CreateReservationDTO;
import pl.sda.reservation.model.Reservation;
import pl.sda.reservation.model.ReservationEvent;
import pl.sda.reservation.service.ReservationEventService;
import pl.sda.reservation.service.ReservationService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/reservation/")
public class ReservationController {


    @Autowired
    private ReservationEventService reservationEventService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path="/add")
    public String add(Model model){
        CreateReservationDTO reservation= new CreateReservationDTO();
        List<ReservationEvent> events= reservationEventService.getAllEvents();
        model.addAttribute("reservation", reservation);
        model.addAttribute("events", events);
        return "reservationAdd";
    }
    @PostMapping(path = "/add")
    public String add(CreateReservationDTO reservation){
        reservationService.addReservation(reservation);
        return "redirect:/addEvent";
    }
    @GetMapping(path = "/list")
    public String list (Model model) {
        List<Reservation> reservationList = reservationService.getReservations();
        model.addAttribute("reservationList", reservationList);
        return "reservationList";
    }
    @GetMapping(path = "/remove/{id}")
    public String removeReservation(@PathVariable(name="id") Long id){
        reservationService.removerReservation(id);
        return "redirect:/reservation/list";
    }
    @GetMapping(path = "/details/{id}")
    public String detailsReservation(Model model, @PathVariable(name="id")Long id){
        Optional<Reservation> reservationOptional= reservationService.find(id);
        if (reservationOptional.isPresent()){
            model.addAttribute("reservations", reservationOptional.get());
            return "reservationDetails";
        }
        return "redirect:/error";
    }
    @PostMapping(path = "/save")
    public String save (Reservation reservation){
        reservationService.saveReservation(reservation);
        return "redirect:/reservation/list";
    }
}

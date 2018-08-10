package pl.sda.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.reservation.model.CreateReservationDTO;
import pl.sda.reservation.model.Reservation;
import pl.sda.reservation.model.ReservationDetailsDto;
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
    public String add(CreateReservationDTO reservation, RedirectAttributes redirect){
        Boolean areThereTickets=reservationEventService.checkTheTicket(reservation.getEventId());
        if(areThereTickets){
            System.out.println(reservationService.addReservation(reservation));
            return "redirect:/reservation/list";
        } else {
            redirect.addFlashAttribute("error_message", "No more tickets available");
            return "redirect:/reservation/list";
        }
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
        if (reservationOptional.isPresent()) {
            Reservation reservation= reservationOptional.get();
            ReservationDetailsDto reservationDetailsDto= new ReservationDetailsDto(
                    reservation.getId(),
                    reservation.getEvent().getId(),
                    reservation.getName(),
                    reservation.getStatus()
            );

            model.addAttribute("reservationdto", reservationDetailsDto);
            return "reservationDetails";
        }else{
            return "error";
        }
    }
    @PostMapping(path = "/details")
    public String setReservationDetails(ReservationDetailsDto reservation) {
        Optional<ReservationEvent> optionalReservationEvent = reservationEventService.find(reservation.getEventId());
        if (optionalReservationEvent.isPresent()) {
            Reservation modifiedReservation = new Reservation(
                    reservation.getId(),
                    reservation.getParticipantName(),
                    optionalReservationEvent.get(),
                    reservation.getStatus()
            );
            reservationService.saveReservation(modifiedReservation);
        }
        return "redirect:/reservation/list";
    }
}

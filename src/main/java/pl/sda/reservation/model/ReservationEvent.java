package pl.sda.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String eventName;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate eventDate;

    private int reservationsLimit;
    private String description;

//    @OneToMany
//    private List<Reservation> reservationList;
}

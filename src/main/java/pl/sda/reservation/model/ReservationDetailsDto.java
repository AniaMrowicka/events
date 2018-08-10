package pl.sda.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailsDto {
    private Long id;
    private Long eventId;
    private String participantName;
    private ReservationStatus status;
}

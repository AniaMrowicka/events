package pl.sda.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    private String password;

    private String name;

    @OneToMany
    private List<Reservation> reservations;

    public AppUser(String userNme, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AppUser() {
    }
}

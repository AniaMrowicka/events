package pl.sda.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAppUserDTO {
    private String userName;
    private String password;
    private String confirm_password;


}

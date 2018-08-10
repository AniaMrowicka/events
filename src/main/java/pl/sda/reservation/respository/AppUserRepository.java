package pl.sda.reservation.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.reservation.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

//    @Query(nativeQuery="select * from 1?")
//    publicList<AppUser>select(String param);
Optional<AppUser> findByUserName(String userName);
}

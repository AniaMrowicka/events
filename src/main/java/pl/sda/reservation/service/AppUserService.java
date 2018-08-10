package pl.sda.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.reservation.model.AppUser;
import pl.sda.reservation.model.RegisterAppUserDTO;
import pl.sda.reservation.respository.AppUserRepository;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;


    public boolean registerUser(RegisterAppUserDTO dto){
//        1.czy istneije uzytkownic o username:
        Optional<AppUser>appUser=appUserRepository.findByUserName(dto.getUserName());
        if (appUser.isPresent()){
            return false;
        }
        AppUser newUser=new AppUser(dto.getUserName(), dto.getPassword());
        appUserRepository.save(newUser);
        return true;

    }
}

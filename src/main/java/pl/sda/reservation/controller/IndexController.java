package pl.sda.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.reservation.model.RegisterAppUserDTO;
import pl.sda.reservation.service.AppUserService;

@Controller
public class IndexController {


    @Autowired
    private AppUserService appUserService;

    @GetMapping(path="/")
        public String index(){
        return "home";
    }
    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }
    @GetMapping(path = "/register")
    public String register(Model model){
        model.addAttribute("user_dto", new RegisterAppUserDTO());
        return "userRegister";
    }
    @PostMapping(path = "/register")
    public String register(Model model, RegisterAppUserDTO registerAppUserDTO){
       if(!registerAppUserDTO.getConfirm_password().equals(registerAppUserDTO.getPassword())) {
           model.addAttribute("user_dto",registerAppUserDTO);

           model.addAttribute("error_message", "Passwords do not match!");
           return "userRegister";
       }
       if (!appUserService.registerUser(registerAppUserDTO)){
           model.addAttribute("user_dto",registerAppUserDTO);
           model.addAttribute("error_message", "This username is taken!");
        return  "userRegister";
       }
        return "redirect:/login";
    }
}

package pl.sda.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/index/")
public class FakeController {


//    @GetMapping(name = "/multiplicationTable")
//    public String getMultiplicationTable(Model model, @RequestParam(name = "size")Integer size) {
//        model.addAttribute("size", size);
//        return "tabliczka";
//    }
    @GetMapping(path="/getalphabet/{howMuch}/")
    public String getAlphabet(Model model, @PathVariable(name = "howMuch")int much){
        List<Character> alphabet=new ArrayList<>();
        for (int i = 97; i <123 ; i++) {
            char j= (char)i;
            alphabet.add(j);
        }
        model.addAttribute("az", alphabet);

    return "alphabet";
    }
}

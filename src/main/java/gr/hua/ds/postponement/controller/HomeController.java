package gr.hua.ds.postponement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
//    @Secured("ROLE_ADMIN")  Για security σε επίπεδο μεθόδου
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    @GetMapping("/403")
//    @Secured("ROLE_ADMIN")  Για security σε επίπεδο μεθόδου
    public String forbidden(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "forbidden";
    }
}

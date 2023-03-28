package jpabook_2nd.jpashop_2nd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("welcome")
    public String welcome(Model model) {
        model.addAttribute("data", "선생님");
        return "welcome";
    }
}

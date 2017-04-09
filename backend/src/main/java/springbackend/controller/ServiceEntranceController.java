package springbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbackend.model.ServiceEntrance;

/**
 * Controller for {@link springbackend.model.ServiceEntrance}'s pages.
 */

@Controller
public class ServiceEntranceController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String serviceEntrance(Model model) {
        model.addAttribute("userForm", new ServiceEntrance());

        return "serviceEntrance";
    }
}

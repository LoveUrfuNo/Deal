package springbackend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbackend.model.ServiceEntrance;
import springbackend.model.User;

/**
 * Controller for {@link springbackend.model.ServiceEntrance}'s pages.
 */

@Controller
public class ServiceEntranceController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String serviceEntrance(Model model) {
        model.addAttribute("userForm", new ServiceEntrance());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().findFirst().orElse(null).getAuthority().equals("ROLE_USER"))
            return "access-denied";
        else
            return "service-entrance";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String main(@ModelAttribute("userForm") ServiceEntrance userForm, Model model) {
        User user = new User();
        model.addAttribute("userForm", user);

        if (userForm.getEnteredPassword().equals(userForm.getCorrectPassword())) {
            return "main";
        } else {
            return "forgot-service-password";
        }
    }
}

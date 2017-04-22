package springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbackend.dao.PersistentUserDao;
import springbackend.model.PersistentUser;
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

        return "serviceEntrance";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String main(@ModelAttribute("userForm") ServiceEntrance userForm, Model model) {
        User user = new User();
        model.addAttribute("userForm", user);

        if (userForm.getEnteredPassword().equals(userForm.getCorrectPassword())) {
            return "main";
        } else {
            return "forgotServicePassword";
        }
    }
}

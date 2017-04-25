package springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbackend.model.Service;
import springbackend.model.User;
import springbackend.service.UserService;

/**
 * Controller for {@link springbackend.model.Service}'s pages.
 */

@Controller
public class ServiceController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/add_service"}, method = RequestMethod.GET)
    public String addService(Model model) {
        model.addAttribute("serviceForm", new Service());

        return "add-service";
    }

    @RequestMapping(value = {"/add_service"}, method = RequestMethod.POST)
    public String addService(@ModelAttribute(value = "serviceForm") Service service, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        service.setId(user.getId());

        String string = service.getNameOfService();
        return "add-service";
    }
}

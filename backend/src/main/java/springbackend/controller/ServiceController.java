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
import springbackend.service.CodingService;
import springbackend.service.ServiceForService;
import springbackend.service.UserService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Controller for {@link springbackend.model.Service}'s pages.
 */

@Controller
public class ServiceController {
    @Autowired
    private CodingService codingService;

    @Autowired
    private ServiceForService serviceForService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add_service", method = RequestMethod.GET)
    public String addService(Model model) {
        model.addAttribute("serviceForm", new Service());

        return "add-service";
    }

    @RequestMapping(value = "/add_service", method = RequestMethod.POST)
    public String addService(@ModelAttribute(value = "serviceForm") Service service, Model model) throws UnsupportedEncodingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        service.setUserId(user.getId());

        if (user.getFirstName() == null)
            service.setUsernameOfSeller(user.getLogin());
        else
            service.setUsernameOfSeller(user.getFirstName());

        service.setNameOfService(codingService.decoding(service.getNameOfService()));
        service.setDescription(codingService.decoding(service.getDescription()));

        this.serviceForService.save(service);

        return "redirect:/redirect";
    }

    @RequestMapping(value = "/show_all_services/{category}", method = RequestMethod.GET)
    public String showAllServicesInGivenCategory(@PathVariable(value = "category") String category, Model model) {
        List<Service> services = this.serviceForService.findAllByCategory(category);

        model.addAttribute("services", services);
        model.addAttribute("category", category);

        return "show-given-services";
    }

    @RequestMapping(value = "/show_your_services", method = RequestMethod.GET)
    public String showUsersServices(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        List<Service> services = this.serviceForService.findAllByUserId(user.getId());
        model.addAttribute("usersServices", services);

        return "user's-services";
    }

    @RequestMapping(value = "/search_services", method = RequestMethod.POST)
    public String searchServices(@ModelAttribute(value = "stringForSearch") String searchLine, Model model) {

        return "searching-results";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteService(@PathVariable(value = "id") Long id) {
        Service service = this.serviceForService.findById(id);
        this.serviceForService.delete(service);

        return "redirect:/show_your_services";
    }
}

package springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbackend.model.Service;
import springbackend.model.User;
import springbackend.service.CodingService;
import springbackend.service.ServiceForService;
import springbackend.service.UserService;
import springbackend.validator.ServiceValidator;

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

    @Autowired
    private ServiceValidator serviceValidator;

    @RequestMapping(value = "/add_service", method = RequestMethod.GET)
    public String addService(Model model) {
        model.addAttribute("serviceForm", new Service());

        return "add-service";
    }

    @RequestMapping(value = "/add_service", method = RequestMethod.POST)
    public String addService(@ModelAttribute(value = "serviceForm") Service service, BindingResult bindingResult) throws UnsupportedEncodingException {
        this.serviceValidator.validate(service, bindingResult);
        if (bindingResult.hasErrors())
            return "add-service";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        service.setUserId(user.getId());
        service.setNameOfService(codingService.decoding(service.getNameOfService()));
        service.setDescription(codingService.decoding(service.getDescription()));

        this.serviceForService.save(service);

        return "redirect:/redirect";
    }

    @RequestMapping(value = "/show_all_services/{category}", method = RequestMethod.GET)
    public String showAllServicesInGivenCategory(@PathVariable(value = "category") String category, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        List<Service> services = this.serviceForService.findAllByCategory(category);

        for(Service s : services) {
            s.setUser(this.userService.findBuId(s.getUserId()));
        }

        model.addAttribute("services", services);
        model.addAttribute("category", category);

        return "show-given-services";
    }

    @RequestMapping(value = "/show_your_services", method = RequestMethod.GET)
    public String showUsersServices(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        user.setServices(this.serviceForService.findAllByUserId(user.getId()));
        model.addAttribute("user", user);
//      model.addAttribute("usersServices", services);

        return "user's-services";
    }

    @RequestMapping(value = "/search_services", method = RequestMethod.POST)
    public String searchServices(@ModelAttribute(value = "stringForSearch") String searchLine, Model model) {

        return "searching-results";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteService(@PathVariable(value = "id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        Service service = this.serviceForService.findById(id);
        if (service.getUserId().equals(user.getId()))
            this.serviceForService.delete(service);
        else
            return "error-page";

        return "redirect:/show_your_services";
    }
}

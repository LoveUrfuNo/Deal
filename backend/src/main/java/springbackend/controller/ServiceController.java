package springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springbackend.model.SearchRequest;
import springbackend.model.Service;
import springbackend.model.User;
import springbackend.model.UserFile;
import springbackend.service.StringService;
import springbackend.service.ServiceForService;
import springbackend.service.UserFileService;
import springbackend.service.UserService;
import springbackend.validator.SearchValidator;
import springbackend.validator.ServiceValidator;
import sun.reflect.generics.tree.Tree;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for {@link springbackend.model.Service}'s pages.
 */

@Controller
public class ServiceController {
    @Autowired
    private StringService stringService;

    @Autowired
    private ServiceForService serviceForService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceValidator serviceValidator;

    @Autowired
    private SearchValidator searchValidator;

    @Autowired
    private UserFileService userFileService;

    @RequestMapping(value = "/add_service", method = RequestMethod.GET)
    public String addService(Model model) {
        model.addAttribute("serviceForm", new Service());

        return "add-service";
    }

    @RequestMapping(value = "/add_service", method = RequestMethod.POST)
    public String addService(@ModelAttribute(value = "serviceForm") Service service,
                             BindingResult bindingResult) {
        this.serviceValidator.validate(service, bindingResult);
        if (bindingResult.hasErrors()) {
            return "add-service";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        service.setUser(user);
        service.setUserId(service.getUser().getId());
        try {
            service.setNameOfService(stringService.decoding(service.getNameOfService()));
            service.setDescription(stringService.decoding(service.getDescription()));

            this.serviceForService.save(service);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "error-page";
        }

        return "redirect";
    }

    @RequestMapping(value = "/show_all_services/{category}", method = RequestMethod.GET)
    public String showAllServicesInGivenCategory(@PathVariable(value = "category") String category,
                                                 Model model) {
        Set<Service> services = this.serviceForService.findAllByCategory(category);

        model.addAttribute("services", services);
        model.addAttribute("category", category);

        return "show-given-services";
    }

    @RequestMapping(value = "/show_your_services", method = RequestMethod.GET)
    public String showUsersServices(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());
        user.getServices().sort(
                (o1, o2) -> o1.getNameOfService().compareToIgnoreCase(o2.getNameOfService()));

        Set<UserFile> allSet = this.userFileService.findAllByUserId(user.getId());
        Map<String, Set<UserFile>> fileMap = new HashMap<>();
        allSet.forEach(t -> fileMap.put(t.getServiceName(),
                new HashSet<>(this.userFileService.findAllByServiceName(t.getServiceName()))));

        model.addAttribute("files", fileMap);
        model.addAttribute("user", user);

        return "user's-services";
    }

    @RequestMapping(value = "/search_services", method = RequestMethod.POST)
    public String searchServices(@ModelAttribute(value = "searchRequest") SearchRequest searchRequest,
                                 Model model,
                                 BindingResult bindingResult) {
        searchValidator.validate(searchRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", new User());
            return "redirect";
        }

        TreeSet<Service> allSet = new TreeSet<>(
                (o1, o2) -> o1.getNameOfService().compareToIgnoreCase(o2.getNameOfService()));
        allSet.addAll(this.serviceForService.findAll());
        Set<Service> resultsSet = allSet.stream()
                .filter(temp ->
                        Arrays.stream(temp.getNameOfService().split(" "))
                                .anyMatch(searchRequest.getSearchLine()::equalsIgnoreCase)
                                ||
                                Arrays.stream(temp.getDescription().split(" "))
                                        .anyMatch(searchRequest.getSearchLine()::equalsIgnoreCase)).collect(Collectors.toSet());
        model.addAttribute("resultsSet", resultsSet);

        return "searching-results";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteService(@PathVariable(value = "id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(auth.getName());

        Service service = this.serviceForService.findById(id);
        if (service.getUserId().equals(user.getId())) {
            this.serviceForService.delete(service);

            this.userFileService.findAllByUserId(user.getId()).stream()
                    .filter(t -> t.getServiceName().equals(service.getNameOfService()))
                    .forEach(temp -> this.userFileService.delete(temp));
        } else {
            return "error-page";
        }

        return "redirect:/show_your_services";
    }
}

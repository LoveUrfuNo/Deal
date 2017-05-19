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
import springbackend.service.*;
import springbackend.validator.SearchValidator;
import springbackend.validator.ServiceValidator;

import java.io.UnsupportedEncodingException;
import java.util.*;

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

    @Autowired
    private SearchService searchService;

    private static final String regexForReplace = "[^а-я\\w-][\\s]{2,}";   //TODO: try to extern from SearchServiceImpl

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

        Map<String, Set<UserFile>> fileMap = new HashMap<>();

        Set<UserFile> allSet = this.userFileService.findAllByUserId(user.getId());
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

        try {
            String sourceSearchLineWithoutMultipleSpaces = searchRequest.getSearchLine().replaceAll(regexForReplace, " ");
            String decodedSearchLine = new String(
                    sourceSearchLineWithoutMultipleSpaces.getBytes("ISO-8859-1"), "UTF-8");

            searchRequest.setSearchLine(decodedSearchLine.toLowerCase());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            model.addAttribute("error_in_site_search", "vvedite drugoe plz");  //TODO: add in jsp
            return "redirect";
        }

        SearchRequest editedSearchRequest
                = this.searchService.getEditedSearchRequest(searchRequest);
        TreeSet<Service> finalSearchResults
                = this.searchService.getResultServiceSet(editedSearchRequest);

        if (searchRequest.getSearchLine().equals(editedSearchRequest.getSearchLine())) {
            Map<String, HashMap<String, Integer>> wordsWithDistance
                    = this.searchService.getWordsWithMinimumDistance(editedSearchRequest);

            String stringWithAmendments = this.searchService.getAlternativeSearchLine(
                    wordsWithDistance, editedSearchRequest);
            model.addAttribute("did_you_meant_it", stringWithAmendments);
        } else {
            model.addAttribute("results_of_the_request_are_shown", editedSearchRequest.getSearchLine());
            model.addAttribute("search_instead_this", searchRequest.getSearchLine());
        }

        model.addAttribute("search_results", finalSearchResults);

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

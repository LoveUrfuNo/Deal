package springbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbackend.dao.RoleDao;
import springbackend.model.Role;
import springbackend.service.EmailService;
import springbackend.model.User;
import springbackend.service.SecurityService;
import springbackend.service.UserDetailsServiceImpl;
import springbackend.service.UserService;
import springbackend.validator.UserValidator;

import java.util.*;

/**
 * Controller for {@link springbackend.model.User}'s pages.
 */

@Controller
public class UserController {

    private static final Long ROLE_USER = 1L;

    private static final Long ROLE_ANONYMOUS = 3L;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String MESSAGE = "message-registration-email.vm";

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model, String error) {
        model.addAttribute("userForm", new User());
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        return "main";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("status", "login");

        return "main";
    }

    @RequestMapping(value = "/profile/{status}", method = RequestMethod.GET)
    public String profile(@PathVariable("status") String status, Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("status", status);

        return "main";
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public String options(Model model) {
        model.addAttribute("userForm", new User());

        List<String> months = new ArrayList<>();
        months.add("Январь");
        months.add("Февраль");
        months.add("Март");
        months.add("Апрель");
        months.add("Май");
        months.add("Июнь");
        months.add("Июль");
        months.add("Август");
        months.add("Сентябрь");
        months.add("Октябрь");
        months.add("Ноябрь");
        months.add("Декабрь");

        model.addAttribute("months", months);

        List<String> numbers = new ArrayList<>();    // TODO: make with streams
        for (Integer i = 1; i <= 31; i++)
            numbers.add(Integer.toString(i));

        model.addAttribute("numbers", numbers);

        return "advanced-options";
    }

    @RequestMapping(value = "/options", method = RequestMethod.POST)
    public String options(@ModelAttribute("userForm") User user) {

        return "redirect:/profile";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "main";
        }

        model.addAttribute("status", "registration");

        UserDetailsServiceImpl.operation = "registration";
        user.setKeyForRegistrationConfirmUrl(EmailService.generateString(32));
        user.setRegistrationConfirmed(false);    //user didn't confirm acc by email message yet
        userService.save(user, ROLE_ANONYMOUS);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
        UserDetailsServiceImpl.operation = null;

        Map map = new HashMap();
        map.put("from", "DEAL");
        map.put("subject", "Hello from " + EmailService.getNameFromEmailAddress(user.getUsername()) + "!");
        map.put("to", user.getUsername());      //TODO: rename field "username" to "email"
        map.put("key_for_registration_confirm_url", user.getKeyForRegistrationConfirmUrl());
        map.put("id", user.getId());

        if (emailService.sendEmail(MESSAGE, map))      //TODO: add output in logger
            System.out.println("Message was sent");
        else
            System.out.println("Error: message wasn't sent");

        return "redirect:/profile/registration";
    }

    @RequestMapping(value = {"/authentication"}, method = RequestMethod.GET)
    public String authentication(Model model) {
        model.addAttribute("userForm", new User());

        return "redirect:/profile";
    }

    @RequestMapping(value = {"/confirm-acc/{token}/{id}"}, method = RequestMethod.GET)
    public String confirmAcc(@PathVariable("token") String token, @PathVariable("id") Long id, Model model) {
        User user = userService.findBuId(id);
        if (!user.getKeyForRegistrationConfirmUrl().equals(token))
            return "redirect:/main?error";

        user.setRegistrationConfirmed(true);
        user.setKeyForRegistrationConfirmUrl(null);

        userService.saveAndFlush(user, ROLE_USER);        //TODO: add output in logger

        return "registration-confirm";
    }








    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "redirect:/profile";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }
}

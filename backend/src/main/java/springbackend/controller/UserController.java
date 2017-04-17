package springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbackend.email.EmailService;
import springbackend.email.Sender;
import springbackend.model.User;
import springbackend.service.SecurityService;
import springbackend.service.UserService;
import springbackend.validator.UserValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for {@link springbackend.model.User}'s pages.
 */

@Controller
public class UserController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("userForm", new User());

        return "main";
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public String options(Model model) {
        model.addAttribute("userForm", new User());

        return "advanced-options";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "main";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "main";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        Map map = new HashMap();
        map.put("from", "DEAL");
        map.put("subject", "Hello from " + Sender.getNameFromEmailAddress(userForm.getUsername()) + "!");
        map.put("to", userForm.getUsername());    // email
        map.put("ccList", new ArrayList<>());
        map.put("bccList", new ArrayList<>());
        map.put("userName", "javastudyUser");
        map.put("urljavastudy", "javastudy.ru");
        map.put("message", null);

        if (emailService.sendEmail("message-registration-email.vm", map))
            System.out.println("message was sent");
        else
            System.out.println("error: message wasn't sent");

        return "redirect:/profile";
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String profile(Model model) {
        model.addAttribute("userForm", new User());

        return "redirect:/registration";
    }

    @RequestMapping(value = {"/confirmAcc"}, method = RequestMethod.GET)
    public String confirmAcc(Model model) {
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

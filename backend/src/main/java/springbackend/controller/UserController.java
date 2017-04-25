package springbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.multipart.MultipartFile;
import springbackend.service.implementation.EmailServiceImpl;
import springbackend.model.User;
import springbackend.service.SecurityService;
import springbackend.service.UserService;
import springbackend.validator.UserOptionsValidator;
import springbackend.validator.UserValidator;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller for {@link springbackend.model.User}'s pages.
 */

@Controller
public class UserController {
    public static final Integer SIZE_OF_GENERATED_STRING = 32;

    private static final Long ROLE_USER = 1L;

    private static final Long ROLE_NOT_ACTIVATED_USER = 3L;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String MESSAGE = "message-registration-email.vm";

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserOptionsValidator optionsValidator;

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String redirect() {
        return "access-denied";
    }

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (user.getFirstName() != null)
            model.addAttribute("name", user.getFirstName());
        else
            model.addAttribute("name", user.getLogin());

        model.addAttribute("userForm", new User());
        model.addAttribute("status", "login");

        return "main";
    }

    @RequestMapping(value = "/profile/{status}", method = RequestMethod.GET)
    public String profile(@PathVariable("status") String status, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        model.addAttribute("name", user.getLogin());
        model.addAttribute("userForm", new User());
        model.addAttribute("status", status);

        String email = user.getUsername(), resultEmailService;
        switch (email.substring(email.indexOf('@') + 1, email.length())) {       //TODO: add more services
            case "gmail.com":
                resultEmailService = "mail.google.com";
                break;
            case "ya.ru":
                resultEmailService = "mail.yandex.ru";
                break;
            case "mail.ru":
                resultEmailService = "e.mail.ru";
                break;
            case "icloud.com":
                resultEmailService = "icloud.com";
                break;
            case "mail.com":
                resultEmailService = "mail.com";
                break;
            default:
                resultEmailService = "dealmarketplace.ru";
        }

        model.addAttribute("email_url", resultEmailService);
        return "main";
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public String options(Model model) {
        model.addAttribute("userOptionForm", new User());

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
    public String options(@ModelAttribute("userOptionForm") User user, BindingResult bindingResult) throws UnsupportedEncodingException {
        optionsValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "advanced-options";

        User resultUser;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().findFirst().orElse(null).getAuthority().equals("ROLE_USER")) {
            resultUser = userService.findByUsername(auth.getName());
        } else if (auth.getAuthorities().stream().findFirst().orElse(null).getAuthority().equals("ROLE_NOT_ACTIVATED_USER")) {
            return "redirect:/profile/registration";
        } else if (auth.getAuthorities().stream().findFirst().orElse(null).getAuthority().equals("ROLE_ANONYMOUS")) {
            return "main";
        } else {
            return "service-entrance";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            resultUser.setDateOfBirth(simpleDateFormat.parse("1997-03-12"));
            resultUser.setFirstName(new String(new String(user.getFirstName().getBytes("ISO-8859-1"), "Cp1251")
                    .getBytes("windows-1251"), "UTF-8"));
            resultUser.setGender(user.getGender());
            resultUser.setCountry(user.getCountry());

            userService.saveAndFlush(resultUser, ROLE_USER);
        } catch (ParseException e) {
            logger.debug(String.format("Error set dateOfBirth for %s", resultUser.getUsername()));
            e.printStackTrace();
        }
        return "redirect:/profile";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "main";

        model.addAttribute("status", "registration");

        user.setKeyForRegistrationConfirmUrl(EmailServiceImpl.generateString(SIZE_OF_GENERATED_STRING));
        user.setRegistrationConfirmed(false);    //user didn't confirm acc by email message yet
        userService.save(user, ROLE_NOT_ACTIVATED_USER);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());

        Map map = new HashMap();
        map.put("from", "DEAL");
        map.put("subject", "Hello from " + EmailServiceImpl.getNameFromEmailAddress(user.getUsername()) + "!");
        map.put("to", user.getUsername());      //TODO: rename field "username" to "email"
        map.put("key_for_registration_confirm_url", user.getKeyForRegistrationConfirmUrl());
        map.put("id", user.getId());

        if (emailServiceImpl.sendEmail(MESSAGE, map))      //TODO: add output in logger
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
    public String welcome() {
        return "welcome";
    }

    // Добавлено быдлом
    @RequestMapping(value = {"/service"}, method = RequestMethod.GET)
    public String service() {
        return "add-service";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }
}
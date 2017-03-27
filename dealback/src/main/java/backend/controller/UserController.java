package backend.controller;

import backend.model.ServiceEntrance;
import backend.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView input() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", new ServiceEntrance());
        modelAndView.setViewName("input/index");

        return modelAndView;
    }

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public ModelAndView inputInMain(@ModelAttribute("userJSP") ServiceEntrance user) {

        ModelAndView modelAndView = new ModelAndView();

        if(user.getEnteredPassword().equals(user.getCorrectPassword())) {
            modelAndView.setViewName("input/main");
            return modelAndView;
        } else {
            modelAndView.setViewName("input/false");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView inputInMain() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("input/main");

        return modelAndView;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signIn() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userData", new User());
        modelAndView.setViewName("input/sign_in");

        return modelAndView;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public ModelAndView signIn(@ModelAttribute("userData") User user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userData", user);
        modelAndView.setViewName("input/welcome");

        return modelAndView;
    }

    @RequestMapping(value = "/checkin", method = RequestMethod.GET)
    public ModelAndView checkIn() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("input/check_in");

        return modelAndView;
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public ModelAndView forgottenPassword() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("input/forgottenPassword");

        return modelAndView;
    }
}

package springbackend.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springbackend.model.SearchRequest;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().findFirst().orElse(null).getAuthority().equals("ROLE_USER")) {
            return "redirect";
        } else {
            return "service-entrance";
        }
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String main(@ModelAttribute("userForm") ServiceEntrance userForm, Model model) {

        JSONObject resultJson = new JSONObject();
        try {
            resultJson.put("language1", "language1");
            resultJson.put("language2", "language2");
            resultJson.put("language3", "language3");
            resultJson.put("language4", "language4");
            resultJson.put("language6", "language6");
            resultJson.put("language7", "language7");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        model.addAttribute("json2", resultJson);
        model.addAttribute("searchRequest", new SearchRequest());

        model.addAttribute("userForm", new User());
        if (userForm.getEnteredPassword().equals(userForm.getCorrectPassword())) {
            return "main";
        } else {
            return "forgot-service-password";
        }
    }

    @RequestMapping(value = "/asd/{request}", method = RequestMethod.GET)
    @ResponseBody
    public String asd(@PathVariable(value = "request") String currentRequest, Model model) {
        JSONObject resultJson = new JSONObject();/*
        try {
            resultJson.put("language1", "C++");
            resultJson.put("language2", "ActionScript");
            resultJson.put("language3", "Clojure");
            resultJson.put("language4", "JavaScript");
            resultJson.put("language5", "Groovy");
            resultJson.put("language6", "Baaa");
            resultJson.put("language7", "aaaaaaaaa");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        try {
            resultJson.put(
                    "num1",
                    currentRequest);
            resultJson.put(
                    "num2",
                    currentRequest + currentRequest);
            resultJson.put(
                    "num3",
                    currentRequest + currentRequest + currentRequest);
            resultJson.put(
                    "num4",
                    currentRequest + currentRequest + currentRequest + currentRequest);
            resultJson.put(
                    "num5",
                    currentRequest + currentRequest + currentRequest + currentRequest + currentRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultJson.toString();
    }
}
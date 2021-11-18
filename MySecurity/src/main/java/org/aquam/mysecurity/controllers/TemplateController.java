package org.aquam.mysecurity.controllers;

import org.aquam.mysecurity.service.registration.RegistrationService;
import org.aquam.mysecurity.users.DefaultUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class TemplateController {

    private final RegistrationService registrationService;

    @Autowired
    public TemplateController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @GetMapping("login")    // addres in browser ("/" + "login" = "/login")
    public String getLoginView() {
        return "login"; // resources/templates/login.html
    }
    @GetMapping("register")    // addres in browser ("/" + "login" = "/login")
    public ModelAndView getRegisterView() {
        ModelAndView modelAndView = new ModelAndView();
        DefaultUser defaultUser = new DefaultUser();
        modelAndView.addObject("defaultuser", defaultUser);
        modelAndView.setViewName("register");
        return modelAndView;

        // User user = new User();
        // modelAndView.addObject("user", user);
        // modelAndView.setViewName("register");
        // return "register"; // folder templates
    }
    @GetMapping("home")    // addres in browser ("/" + "login" = "/login")
    public String getHomeView() {
        return "home"; // folder templates
    }

    @GetMapping("courses")    // addres in browser ("/" + "login" = "/login")
    public String getCourses() {
        return "courses"; // folder templates
    }

    @PostMapping("register")
    public ModelAndView registerUser(@Valid DefaultUser defaultUser, BindingResult bindingResult, ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors");
            modelMap.addAttribute("bindingResult", bindingResult); // displ validation messages
        }
        else
            registrationService.register(defaultUser.getName(), defaultUser.getUsername(), defaultUser.getPassword());
            // save

        modelAndView.addObject("defaultuser", new DefaultUser());
        modelAndView.setViewName("register");

        return modelAndView;
    }


}

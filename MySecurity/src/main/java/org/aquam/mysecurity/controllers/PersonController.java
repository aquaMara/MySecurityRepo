package org.aquam.mysecurity.controllers;

import org.aquam.mysecurity.service.registration.RegistrationRequest;
import org.aquam.mysecurity.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "users/registration")
public class PersonController {

    private final RegistrationService registrationService;

    @Autowired
    public PersonController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping // недоступно тк в секьюрити настройки на логин всем доступ
    // остальное только зошедшим
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }


}

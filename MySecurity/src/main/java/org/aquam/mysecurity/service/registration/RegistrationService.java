package org.aquam.mysecurity.service.registration;

import org.aquam.mysecurity.service.DefaultUserService;
import org.aquam.mysecurity.users.DefaultUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final DefaultUserService defaultUserService;

    @Autowired
    public RegistrationService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }


    public String register(RegistrationRequest request) {
        return defaultUserService.signUpDefaultUser(new DefaultUser(request.getName(), request.getUsername(), request.getPassword(), true, true));
    }

    public String register(String name, String username, String password) {
        return defaultUserService.signUpDefaultUser(name, username, password);
    }


}

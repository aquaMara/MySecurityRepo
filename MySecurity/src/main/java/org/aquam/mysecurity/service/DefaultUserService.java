package org.aquam.mysecurity.service;

import org.aquam.mysecurity.users.DefaultUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class DefaultUserService implements UserDetailsService {

    private static final String DEFAULT_USER_NOT_FOUND = "Person with username %s not found";
    private final DefaultUserRepository defaultUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DefaultUserService(DefaultUserRepository defaultUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.defaultUserRepository = defaultUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return defaultUserRepository.findByUsername(username)
                                    .orElseThrow(() -> new UsernameNotFoundException(String.format(DEFAULT_USER_NOT_FOUND, username)));

    }

    public String signUpDefaultUser(DefaultUser defaultUser) {

        boolean defaultUserExists = defaultUserRepository.findByUsername(defaultUser.getUsername()).isPresent();

        if (!defaultUserExists)
            throw new IllegalStateException("Username already taken");

        String encodedPassword = bCryptPasswordEncoder.encode(defaultUser.getPassword());
        defaultUser.setPassword(encodedPassword);

        defaultUserRepository.save(defaultUser);

        return "It works!";

    }

    public String signUpDefaultUser(String name, String username, String password) {

        boolean defaultUserExists = defaultUserRepository.findByUsername(username).isPresent();

        if (!defaultUserExists)
            throw new IllegalStateException("Username already taken");

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        DefaultUser defaultUser = new DefaultUser(name, username, encodedPassword, true, true);

        defaultUserRepository.save(defaultUser);

        return "It works!";

    }


}

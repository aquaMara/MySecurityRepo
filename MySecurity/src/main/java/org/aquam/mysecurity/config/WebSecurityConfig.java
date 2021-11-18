package org.aquam.mysecurity.config;

import org.aquam.mysecurity.service.DefaultUserService;
import org.aquam.mysecurity.users.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // private final PersonService personService;
    private final DefaultUserService defaultUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConfig(DefaultUserService defaultUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.defaultUserService = defaultUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/home/**").hasAnyRole(AppUserRole.PERSON.name(), AppUserRole.ADMIN.name())
                    //.antMatchers("/admins/**").hasRole(AppUserRole.ADMIN.name())
                    //.antMatchers("/people/**").hasRole(AppUserRole.PERSON.name())
                    // .antMatchers("/registration/**").permitAll() // any req of this endpoint is allowed
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/home", true) // true to force redirect
                    .usernameParameter("usernamexyz")
                    .passwordParameter("passwordxyz")  // param - in form - name
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // this MUST be POST if csrf is enabled; whenever we go to this /logout url with this GET method, we logout
                    //.clearAuthentication(true)
                    .logoutSuccessUrl("/");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(defaultUserService);
        return provider;
    }

}

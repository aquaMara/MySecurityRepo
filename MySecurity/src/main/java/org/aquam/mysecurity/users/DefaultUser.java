package org.aquam.mysecurity.users;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class DefaultUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Name is compulsory")
    private String name;
    @NotNull(message = "Username is compulsory")
    private String username;
    @NotNull(message = "Password is compulsory")
    @Length(min=5, message = "Password should be at least 5 characters")
    private String password;
    private AppUserRole appUserRole;
    private boolean locked = false;
    private boolean enabled = true;

    @ManyToMany
    @JoinTable(
            name="built_homes",
            joinColumns = @JoinColumn(name="personId"),
            inverseJoinColumns = @JoinColumn(name="homeId")
    )
    private List<Home> homesForPerson = new ArrayList<>();

    public void addHomesForPerson(Home home) {
        homesForPerson.add(home);
    }

    public DefaultUser(String name, String username, String password, boolean locked, boolean enabled) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.appUserRole = AppUserRole.PERSON;
        this.locked = false;
        this.enabled = true;
    }

    public DefaultUser() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singleton(authority); //Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }


    public List<Home> getHomesForPerson() {
        return homesForPerson;
    }

    public void setHomesForPerson(List<Home> homesForPerson) {
        this.homesForPerson = homesForPerson;
    }
}

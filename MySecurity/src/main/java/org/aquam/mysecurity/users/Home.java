package org.aquam.mysecurity.users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeId;
    private String homeName;

    @ManyToMany(mappedBy = "homesForPerson")
    private List<DefaultUser> peopleForHome = new ArrayList<>();

    public void addPeopleForHome(DefaultUser defaultUser) {
        peopleForHome.add(defaultUser);
    }



    // one home has many rooms
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // little letter!!!
    @JoinColumn(name = "homeId")
    private List<Room> roomsForHome = new ArrayList<>();

    public void addRoomsForHome(Room room) {
        roomsForHome.add(room);
    }

    public Home() {}

    public Home(String homeName) {
        this.homeName = homeName;
    }

    public Home(DefaultUser defaultUser, String homeName) {
        this.homeName = homeName;
        defaultUser.setId(defaultUser.getId());
    }

    public Long getHomeId() {
        return homeId;
    }

    public void setHomeId(Long homeId) {
        this.homeId = homeId;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public List<Room> getRoomsForHome() {
        return roomsForHome;
    }

    public void setRoomsForHome(List<Room> roomsForHome) {
        this.roomsForHome = roomsForHome;
    }

    @Override
    public String toString() {
        return "Home{" +
                "homeId=" + homeId +
                ", homeName='" + homeName;
    }
}

/*
    public List<Person> getPeopleForHome() {
        return peopleForHome;
    }

    public void setPeopleForHome(List<Person> peopleForHome) {
        this.peopleForHome = peopleForHome;
    }


 */
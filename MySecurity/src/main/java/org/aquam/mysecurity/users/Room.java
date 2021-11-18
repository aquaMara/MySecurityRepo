package org.aquam.mysecurity.users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "homeId")
    private Home home;

    // @OneToMany(mappedBy = "room")
    @OneToMany
    @JoinColumn(name="roomId")
    private List<Note> notesForRoom = new ArrayList<>();

    public void addNotesForRoom(Note note) {
        notesForRoom.add(note);
    }

    public Room() {
    }

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName;
    }
}

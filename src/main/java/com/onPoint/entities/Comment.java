package com.onPoint.entities;

import javax.persistence.*;

/**
 * Created by vajrayogini on 4/6/16.
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @ManyToOne
    VolunteerProfile volunteerProf;

    @ManyToOne
    User user;

    public Comment() {
    }

    public Comment(String text, VolunteerProfile volunteerProf, User user) {
        this.text = text;
        this.volunteerProf = volunteerProf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public VolunteerProfile getVolunteer() {
        return volunteerProf;
    }

    public void setVolunteer(VolunteerProfile volunteer) {
        this.volunteerProf = volunteer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

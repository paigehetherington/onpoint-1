package com.onPoint.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.javafx.beans.IDProperty;
import org.springframework.boot.orm.jpa.EntityScan;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vajrayogini on 4/5/16.
 */
@Entity
@Table(name = "users")
public class User {
    public enum UserType {
        Admin,
        Volunteer
    }
    @Id
    @GeneratedValue
    int id;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<ServiceOrg> serviceOrg;

    @OneToOne
    VolunteerProfile volunteerProfile;


    @Column(nullable = false)
    UserType userType;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String email;


    public User(){

    }

    public User(UserType userType, String username, String password, String email) {
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

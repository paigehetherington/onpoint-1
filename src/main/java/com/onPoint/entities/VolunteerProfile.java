package com.onPoint.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vajrayogini on 4/5/16.
 */
@Entity
@Table(name = "volunteer_profiles")
public class VolunteerProfile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String organization;
    @Column(nullable = false)
    String country;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    String photo;
//    @Column(nullable = false)
//    int rating;

    @OneToOne//(mappedBy = "user") //?
    User user;

    @OneToMany(mappedBy = "volunteerProf")
    @JsonIgnore //with any ONe to Many otherwise can have unlimited recursion --> "broken pipe"
    List<Comment> comments;

    public VolunteerProfile() {
    }

    public VolunteerProfile(String name, String organization, String country, String description, String photo) {
        this.name = name;
        this.organization = organization;
        this.country = country;
        this.description = description;
        this.photo = photo;
//        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
}

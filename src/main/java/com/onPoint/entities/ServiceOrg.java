package com.onPoint.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vajrayogini on 4/5/16.
 */
@Entity
@Table(name = "service_orgs")
public class ServiceOrg {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String photo;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    String regions;

    @Column(nullable = false)
    String donateLink;

    @Column(nullable = false)
    String volunteerLink;

    @ManyToOne
    User user;

    @OneToMany(mappedBy = "serviceOrg")
    @JsonIgnore
    List<Rating> ratings;

    public ServiceOrg() {
    }

    public ServiceOrg(String photo, String name, String description, String regions, String donateLink, String volunteerLink) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.regions = regions;
        this.donateLink = donateLink;
        this.volunteerLink = volunteerLink;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getDonateLink() {
        return donateLink;
    }

    public void setDonateLink(String donateLink) {
        this.donateLink = donateLink;
    }

    public String getVolunteerLink() {
        return volunteerLink;
    }

    public void setVolunteerLink(String volunteerLink) {
        this.volunteerLink = volunteerLink;
    }
}

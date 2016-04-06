package com.onPoint.entities;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

/**
 * Created by vajrayogini on 4/5/16.
 */
@Entity
@Table(name = "volunteer-profiles")
public class VolunteerProfile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;
    String organization;
    String country;
    String description;
    String photo;

    public VolunteerProfile() {
    }

    public VolunteerProfile(String name, String organization, String country, String description, String photo) {
        this.name = name;
        this.organization = organization;
        this.country = country;
        this.description = description;
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
}

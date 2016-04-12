package com.onPoint.entities;

import javax.persistence.*;

/**
 * Created by vajrayogini on 4/12/16.
 */
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    int stars;

    @ManyToOne
    User user;

    @ManyToOne
    ServiceOrg serviceOrg;


    public Rating() {
    }

    public Rating(int stars, User user, ServiceOrg serviceOrg) {
        this.stars = stars;
        this.user = user;
        this.serviceOrg = serviceOrg;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceOrg getServiceOrg() {
        return serviceOrg;
    }

    public void setServiceOrg(ServiceOrg serviceOrg) {
        this.serviceOrg = serviceOrg;
    }
}

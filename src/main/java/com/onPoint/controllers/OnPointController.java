package com.onPoint.controllers;

import com.onPoint.entities.ServiceOrg;
import com.onPoint.entities.User;
import com.onPoint.entities.VolunteerProfile;
import com.onPoint.services.ServiceOrgRepository;
import com.onPoint.services.UserRepository;
import com.onPoint.services.VolunteerProfileRepository;
import com.onPoint.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vajrayogini on 4/5/16.
 */
@RestController
public class OnPointController {

    @Autowired
    VolunteerProfileRepository volunteers;

    @Autowired
    UserRepository users;

    @Autowired
    ServiceOrgRepository serviceOrgs;

    Server dbui = null;

    @PostConstruct
    public void init() throws SQLException, PasswordStorage.CannotPerformOperationException {
        dbui = Server.createWebServer().start();
        //hardcode the admin login info
        if (users.count() == 0) {
            User user = new User();
            user.setUsername("paigeCandace");
            user.setPassword(PasswordStorage.createHash("ironyard"));
            user.setEmail("pc@ironyard.com");
            user.setUserType(User.UserType.Admin);
            users.save(user);
        }
    }

    @PreDestroy
    public void destroy() {
        dbui.stop();
    }


@RequestMapping(path = "/register", method = RequestMethod.POST)
    public User register (@RequestBody User tempUser, HttpSession session) throws Exception {
    User user = users.findByUsername(tempUser.getUsername());
    if (user == null) {
        user = new User(User.UserType.Volunteer, tempUser.getUsername(), PasswordStorage.createHash(tempUser.getPassword()), tempUser.getEmail());
        users.save(user);
        session.setAttribute("username", user.getUsername());
    } else {
        throw new Exception("Username already exists.");
    }
    return user;

}

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login (@RequestBody User tempUser, HttpSession session) throws Exception {
        User user = users.findByUsername(tempUser.getUsername());
        if (user == null) {
            throw new Exception("Username does not exist.");
        }
        else if (!PasswordStorage.verifyPassword(tempUser.getPassword(), user.getPassword())) {
            throw new Exception("Incorrect password.");
        }
        session.setAttribute("username", user.getUsername());
        return user;
        }


    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout (HttpSession session) {
        session.invalidate();
    }

    @RequestMapping(path = "/service-org", method = RequestMethod.GET)
    public List<ServiceOrg> getServiceOrgs() {
        return (List<ServiceOrg>) serviceOrgs.findAll();
    }

    @RequestMapping(path = "/service-org", method = RequestMethod.POST)
    public void addServiceOrg (@RequestBody ServiceOrg serviceOrg, HttpSession session) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        if (user.getUserType() != User.UserType.Admin) {
            throw new Exception ("Only Admin can add Service Organizations.");
        }
        serviceOrgs.save(serviceOrg);
    }

    @RequestMapping(path = "/service-org", method = RequestMethod.PUT)
    public void updateServiceOrg (HttpSession session, @RequestBody ServiceOrg serviceOrg) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        if (user.getUserType() != User.UserType.Admin) {
            throw new Exception("Only Admin can update Service Organizations.");
        }
        serviceOrgs.save(serviceOrg);
    }

    @RequestMapping(path = "/service-org/{id}", method = RequestMethod.DELETE)
    public void deleteServiceOrg (@PathVariable int id, HttpSession session) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        if (user.getUserType() != User.UserType.Admin) {
            throw new Exception("Only Admin can delete Service Organizations.");
        }
        serviceOrgs.delete(serviceOrgs.findOne(id));
    }

    @RequestMapping(path = "/volunteer-profile", method = RequestMethod.POST)
    public void updateVolunteerProfile (HttpSession session, @RequestBody VolunteerProfile volunteerProfile) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        if (user == null) {
            throw new Exception("You must be logged in to create a Volunteer Profile.");
        }
        volunteers.save(volunteerProfile);
    }




}

package com.onPoint.controllers;

import com.onPoint.entities.User;
import com.onPoint.services.ServiceOrgRepository;
import com.onPoint.services.UserRepository;
import com.onPoint.services.VolunteerProfileRepository;
import com.onPoint.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

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
    public void init() throws SQLException {
        dbui = Server.createWebServer().start();
        //hardcode the admin login info
        if (users == null) {
            User user = new User();
            user.setUsername("paigeCandace");
            user.setPassword("ironyard");
            user.setEmail("pc@ironyard.com");
            user.setUserType(User.UserType.Admin);
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




}

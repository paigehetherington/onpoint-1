package com.onPoint.controllers;

import com.onPoint.entities.*;
import com.onPoint.services.*;
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

    @Autowired
    CommentRepository comments;

    @Autowired
    RatingRepository ratings;

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

            if (volunteers.count() == 0) {
            VolunteerProfile volunteerProfile = new VolunteerProfile();
            volunteerProfile.setName("Paige Hetherington");
            volunteerProfile.setOrganization("Acupuncturists Without Borders");
            volunteerProfile.setCountry("Nepal");
            volunteerProfile.setPhoto("https://scontent-mia1-1.xx.fbcdn.net/v/t1.0-9/10151411_10154269947604881_8649058420500808007_n.jpg?oh=63a7da994477706d8c36476da7a81db4&oe=5780FDDC");
            volunteerProfile.setDescription("Amazing people. I felt I received more than I gave in Nepal. Their resilience in the face of tragedy is rooted in community.");
//            volunteerProfile.setRating(5)
            ;
            VolunteerProfile volunteerProfile2 = new VolunteerProfile();
            volunteerProfile2.setName("Lauren Freiman");
            volunteerProfile2.setOrganization("Global Acupuncture Project");
            volunteerProfile2.setCountry("Mexico");
            volunteerProfile2.setPhoto("http://www.globalacupuncture.org/images/100_0599_ld.jpg");
            volunteerProfile2.setDescription("It was invaluable being of service to this community. They expressed such gratitude and I felt deeply honored to do what I could to help.");
//            volunteerProfile2.setRating(4);

            VolunteerProfile volunteerProfile3 = new VolunteerProfile();
            volunteerProfile3.setName("Joanna Smith");
            volunteerProfile3.setOrganization("Barefoot Acupuncturists");
            volunteerProfile3.setCountry("India");
            volunteerProfile3.setPhoto("http://acupuncteur-apn.click2thepoint.com/Blog/wp-content/uploads/2010/09/7.jpg");
            volunteerProfile3.setDescription("I was unaware of the limited resources in Mumbai. There was a great need for our services and I will continue to take trips with this organization in the future.");
//            volunteerProfile3.setRating(4);

            volunteers.save(volunteerProfile);
            volunteers.save(volunteerProfile2);
            volunteers.save(volunteerProfile3);
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
    public void createServiceOrg (@RequestBody ServiceOrg serviceOrg, HttpSession session) throws Exception {
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
    public void createVolunteerProfile (HttpSession session, @RequestBody VolunteerProfile volunteerProfile) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        volunteerProfile.setUser(user);
        if (user == null) {
            throw new Exception("You must be logged in to create a Volunteer Profile.");
        }
        volunteers.save(volunteerProfile);
        System.out.println("Create Volunteer");
    }

    @RequestMapping(path = "/volunteer-profile", method = RequestMethod.GET)
    List<VolunteerProfile> getVolunteers() {
        System.out.println("show volunteers");
        return (List<VolunteerProfile>) volunteers.findAll();

    }

    @RequestMapping(path = "/volunteer-profile", method = RequestMethod.PUT)
    public void updateVolunteerProfile (HttpSession session, @RequestBody VolunteerProfile volunteerProfile) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        volunteerProfile.setUser(user);
        if (user == null) {
            throw new Exception("You must be logged in to update a Volunteer Profile");
        }
        volunteers.save(volunteerProfile);
        System.out.println("Update Volunteer");
    }

    @RequestMapping(path = "/volunteer-profile/{id}", method = RequestMethod.DELETE)
    public void deleteVolunteerProfile (HttpSession session, @PathVariable int id) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        VolunteerProfile volunteerProfile = volunteers.findOne(id);
        if (volunteerProfile.getUser().getId() != user.getId()) {
            throw new Exception("You must be logged in to delete a Volunteer Profile.");
        }
        volunteers.delete(volunteerProfile);

    }

    @RequestMapping(path = "/comment", method = RequestMethod.GET)
    public List<Comment> getComments() {
       return (List<Comment>) comments.findAll();
    }

    // {text: "This is a comment", volunteerProf: {id: 10}} this is how candance will pass in JS
    @RequestMapping(path ="/comment", method = RequestMethod.POST)
    public void createComment(@RequestBody Comment comment, HttpSession session) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        comment.setUser(user);
        if (user == null) {
            throw new Exception("You must be logged in to make a comment.");
        }
        comments.save(comment);
    }

    @RequestMapping(path = "/comment", method = RequestMethod.PUT)
    public void updateComment (HttpSession session, @RequestBody Comment comment) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        comment.setUser(user);
        if (user == null) {
            throw new Exception("You can only edit your own comment.");
        }
        comments.save(comment);
    }

    @RequestMapping(path = "/comment/{id}", method = RequestMethod.DELETE)
    public void deleteComment(HttpSession session, @PathVariable int id) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        Comment comment = comments.findOne(id);
        if (comment.getUser().getId() != user.getId()) {
            throw new Exception("You can only delete your own comments.");
        }
        comments.delete(comment);
    }
//
//    @RequestMapping(path = "/rating", method = RequestMethod.POST)
//    public void createRating(HttpSession session, @RequestBody Rating rating) {
//        User user = users.findByUsername((String) session.getAttribute("username"));
//        rating.setUser(user);
//    }






}

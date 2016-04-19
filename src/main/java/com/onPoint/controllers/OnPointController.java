package com.onPoint.controllers;

import com.onPoint.entities.*;
import com.onPoint.services.*;
import com.onPoint.utils.PasswordStorage;
import org.h2.server.Service;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
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

        if (serviceOrgs.count() == 0) {
            ServiceOrg serviceOrg = new ServiceOrg();
            serviceOrg.setName("Project Buena Vista");
            serviceOrg.setDescription("Medical outreach to provide free and effective health care to people " +
                    "who have minimal access. Communities located in the rainforest must have medical care in " +
                    "order to protect their environment. Project Buena Vista beleives in a conservation mission which " +
                    "provides educational opportunities and health care to the people who live in the rain forest.");
            serviceOrg.setRegions("Southeastern Peru");
            serviceOrg.setDonateLink("http://www.projectbuenavista.org/contactus.html");
            serviceOrg.setVolunteerLink("http://www.projectbuenavista.org/programs.html");
            serviceOrg.setPhoto("http://www.projectbuenavista.org/index.html");

            ServiceOrg serviceOrg2 = new ServiceOrg();
            serviceOrg2.setName("Acupuncture Relief Project");
            serviceOrg2.setDescription("Since 2008, the Acupuncture Relief Project has provided over 140,000" +
                    "treatments to patients living in rural villages outside of Kathmandu Nepal. Our efforts include " +
                    " the treatment of patients living with HIV and AIDs as well as people suffering from extreme poverty" +
                    " and social disfranchisement.");
            serviceOrg2.setRegions("Nepal");
            serviceOrg2.setDonateLink("http://acupuncturereliefproject.org/donate");
            serviceOrg2.setVolunteerLink("http://acupuncturereliefproject.org/volunteer/programs");
            serviceOrg2.setPhoto("http://acupuncturereliefproject.org/");

            ServiceOrg serviceOrg3 = new ServiceOrg();
            serviceOrg3.setName("Acupuncturists Without Borders");
            serviceOrg3.setDescription("AWB provides relief and recovery in the wake of major disasters and traumatic events." +
                    " Clinics utilize auriuclar acupuncture to treat post traumatic stress. AWB provides trainings " +
                    "for their volunteers and also offers clinics for veterans. Their work is domestic and international. ");
            serviceOrg3.setRegions("US, Haiti, Nepal, Mexico, Mongolia, Greece");
            serviceOrg3.setDonateLink("https://donatenow.networkforgood.org/1443018?code=Home+Page");
            serviceOrg3.setVolunteerLink("http://www.acuwithoutborders.org/volunteer.php");
            serviceOrg3.setPhoto("http://www.acuwithoutborders.org/volunteer.php");

            ServiceOrg serviceOrg4 = new ServiceOrg();
            serviceOrg4.setName("Mindful Medicine Worldwide");
            serviceOrg4.setDescription("MMW brings long-term integrative health care to people of developing areas, " +
                    "by establishing and operating free integrative health care clinics. Their current focus is on " +
                    "supporting areas affected by the earthquakes of 2015.");
            serviceOrg4.setRegions("Nepal, Thailand");
            serviceOrg4.setDonateLink("https://donatenow.networkforgood.org/1430088");
            serviceOrg4.setVolunteerLink("http://www.mindfulmedicineworldwide.org/#!volunteer-requirements/ckz7");
            serviceOrg4.setPhoto("http://www.mindfulmedicineworldwide.org/");

            ServiceOrg serviceOrg5 = new ServiceOrg();
            serviceOrg5.setName("Global Acupuncture Project");
            serviceOrg5.setDescription("The PanAfrican Acupuncture Project trains local healthcare workers to use " +
                    "simple and effective acupuncture techniques that enable them to treat the symptoms associated " +
                    "with HIV/AIDS, malaria, TB, and other delibilitating conditions.");
            serviceOrg5.setRegions("Africa, Mexico");
            serviceOrg5.setDonateLink("http://www.globalacupuncture.org/support.html");
            serviceOrg5.setVolunteerLink("http://www.globalacupuncture.org/involvement.html");
            serviceOrg5.setPhoto("http://www.globalacupuncture.org/index.html");

            ServiceOrg serviceOrg6 = new ServiceOrg();
            serviceOrg6.setName("Projects Abroad");
            serviceOrg6.setDescription("Professional acupuncture volunteers work at a private hospital in " +
                    "Kathmandu, Nepal.");
            serviceOrg6.setRegions("Nepal");
            serviceOrg6.setDonateLink("http://www.projects-abroad.org/volunteer-projects/projects-for-professionals" +
                    "/medicine-and-healthcare/acupuncturist/volunteer-nepal/");
            serviceOrg6.setVolunteerLink("http://www.projects-abroad.org/volunteer-projects/projects-for-professionals" +
                    "/medicine-and-healthcare/acupuncturist/volunteer-nepal/");
            serviceOrg6.setPhoto("http://www.projects-abroad.org/volunteer-projects/projects-for-professionals/" +
                    "medicine-and-healthcare/acupuncturist/volunteer-nepal/");

            ServiceOrg serviceOrg7 = new ServiceOrg();
            serviceOrg7.setName("Barefoot Acupuncturists");
            serviceOrg7.setDescription("Barefoot Acupuncturists’ objective is to offer an efficient and affordable medicine " +
                    "to society's poorest and least fortunate. In order to treat populations who have insufficient access to " +
                    "healthcare, they open, operate and oversee low cost acupuncture clinics in underprivileged areas.\n" +
                    "They also train local people in acupuncture who will be able to practice in their own communities.");
            serviceOrg7.setRegions("India");
            serviceOrg7.setDonateLink("http://www.barefootacupuncturists.com/en/participate.html");
            serviceOrg7.setVolunteerLink("http://www.barefootacupuncturists.com/en/participate.html");
            serviceOrg7.setPhoto("http://www.barefootacupuncturists.com/en/participate.html\n");

            ServiceOrg serviceOrg8 = new ServiceOrg();
            serviceOrg8.setName("Acupuncture Ambassadors");
            serviceOrg8.setDescription("Researching the spread and growth of acupuncture’s role in humanitarian efforts.");
            serviceOrg8.setRegions("Nepal, Spain, Japan, Jordan");
            serviceOrg8.setDonateLink("http://acupunctureambassadors.org/donate.html");
            serviceOrg8.setVolunteerLink("http://acupunctureambassadors.org/donate.html");
            serviceOrg8.setPhoto("http://acupunctureambassadors.org/whatisHum-Acu.html");

            ServiceOrg serviceOrg9 = new ServiceOrg();
            serviceOrg9.setName("Global Clinic");
            serviceOrg9.setDescription("Global Clinic is committed to advancing a global network that creates practical" +
                    " and sustainable health solutions for those in need. The pursuit of Global Clinic is to provide " +
                    "and maintain a sustainable network of integrative, holistic medical practices to populations with " +
                    "limited or no access to conventional healthcare. Acupuncture is one of the modalities they provide " +
                    "in their clinics.");
            serviceOrg9.setRegions("Dominican Republic, Haiti, Cuba, Ecuador, Romania, Guatemala, India");
            serviceOrg9.setDonateLink("http://theglobalclinic.org/donate/");
            serviceOrg9.setVolunteerLink("http://theglobalclinic.org/volunteer-information/");
            serviceOrg9.setPhoto("http://theglobalclinic.org/");

            ServiceOrg serviceOrg10 = new ServiceOrg();
            serviceOrg10.setName("Guatemala Acupuncture and Medical Aid Project");
            serviceOrg10.setDescription(" GUAMAP is a Traditional Chinese Medicine (TCM) acupuncture " +
                    "training project that uses a “barefoot doctor” model for rural community health care.");
            serviceOrg10.setRegions("Guatemala");
            serviceOrg10.setDonateLink("http://www.guamap.net/index.php/donate/");
            serviceOrg10.setVolunteerLink("http://www.guamap.net/index.php/volunteer/");
            serviceOrg10.setPhoto("http://www.guamap.net/index.php/home/");

            ServiceOrg serviceOrg11 = new ServiceOrg();
            serviceOrg11.setName("One World Health Project");
            serviceOrg11.setDescription("OWHP’s mission is to positively impact global health " +
                    "by promoting wellness education and increasing access to acupuncture in under served communities.");
            serviceOrg11.setRegions("Peru, Sri Lanka, Kenya, US");
            serviceOrg11.setDonateLink("http://www.owhp.org/donate/");
            serviceOrg11.setVolunteerLink("http://www.owhp.org");
            serviceOrg11.setPhoto("http://www.owhp.org/mission/");

            ServiceOrg serviceOrg12 = new ServiceOrg();
            serviceOrg12.setName("World Medicine");
            serviceOrg12.setDescription("World Medicine is a registered UK charity providing acupuncture to people " +
                    "around the world suffering the effects of trauma, disaster and poverty.");
            serviceOrg12.setRegions("India");
            serviceOrg12.setDonateLink("http://worldmedicine.org.uk/index.php/support-us");
            serviceOrg12.setVolunteerLink("http://worldmedicine.org.uk/index.php/projects/work-with-us");
            serviceOrg12.setPhoto("http://worldmedicine.org.uk/");

            serviceOrgs.save(serviceOrg);
            serviceOrgs.save(serviceOrg2);
            serviceOrgs.save(serviceOrg3);
            serviceOrgs.save(serviceOrg4);
            serviceOrgs.save(serviceOrg5);
            serviceOrgs.save(serviceOrg6);
            serviceOrgs.save(serviceOrg7);
            serviceOrgs.save(serviceOrg8);
            serviceOrgs.save(serviceOrg9);
            serviceOrgs.save(serviceOrg10);
            serviceOrgs.save(serviceOrg11);
            serviceOrgs.save(serviceOrg12);

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
        System.out.println("You are successfully logged out.");
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
    public VolunteerProfile createVolunteerProfile (HttpSession session, @RequestBody VolunteerProfile volunteerProfile) throws Exception {
        String userName = (String) session.getAttribute("username");
        User user = users.findByUsername(userName);
        volunteerProfile.setUser(user);
        if (user == null) {
            throw new Exception("You must be logged in to create a Volunteer Profile.");
        }
        System.out.println("Create Volunteer");
        return volunteers.save(volunteerProfile);
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
        //volunteers.findOne()
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

    @RequestMapping(path = "/comment", method = RequestMethod.GET) //to filter by volProfile do path Var with id
    public List<Comment> getComments() {
       return (List<Comment>) comments.findAll();
    }

    // {text: "This is a comment", volunteerId: 10} this is how candance will pass in JS
    @RequestMapping(path ="/comment", method = RequestMethod.POST)
    public Comment createComment(@RequestBody HashMap data, HttpSession session) throws Exception {
        String text = (String) data.get("text");
        int id = (int) data.get("volunteerId");
        String username = (String) session.getAttribute("username");
        User user = users.findByUsername(username);
        if (user == null) {
            throw new Exception("You must be logged in to make a comment.");
        }

        Comment comment = new Comment(text, volunteers.findOne(id), user);
        return comments.save(comment);
    }

    @RequestMapping(path = "/comment", method = RequestMethod.PUT)
    public void updateComment (HttpSession session, @RequestBody Comment comment) throws Exception {
        User user = users.findByUsername((String) session.getAttribute("username"));
        comment.setUser(user);
        comment.setVolunteerProf(comment.getVolunteerProf());
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

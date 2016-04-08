package com.onPoint;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onPoint.entities.ServiceOrg;
import com.onPoint.entities.User;
import com.onPoint.entities.VolunteerProfile;
import com.onPoint.services.CommentRepository;
import com.onPoint.services.ServiceOrgRepository;
import com.onPoint.services.UserRepository;
import com.onPoint.services.VolunteerProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OnPointApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnPointApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Autowired
	CommentRepository comments;

	@Autowired
	ServiceOrgRepository serviceOrgs;

	@Autowired
	UserRepository users;

	@Autowired
	VolunteerProfileRepository volunteerProfs;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	// register
	@Test
	public void test1register() throws Exception {
		User user = new User();
		user.setUsername("Paige");
		user.setEmail("h@g.com");
		user.setPassword("123");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/register")
						.content(json)
						.contentType("application/json")
		);
		Assert.assertTrue(users.count() == 2);


	}

	@Test
	public void test2login() throws Exception {
		User user = new User();
		user.setUsername("Paige");
		user.setPassword("123");
		//user.setEmail("h@g.com");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		ResultActions ra = mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
						.content(json)
						.contentType("application/json")
		);
		MvcResult result = ra.andReturn();
		MockHttpServletRequest request = result.getRequest();
		HttpSession session = request.getSession();
		Assert.assertTrue(session.getAttribute("username") != null);
	}

	//add ServiceOrg
	@Test
	public void test3ServiceOrg() throws Exception {
		ServiceOrg serviceOrg = new ServiceOrg();
		serviceOrg.setName("AWB");
		serviceOrg.setDescription("international aid");
		serviceOrg.setDonateLink("url");
		serviceOrg.setVolunteerLink("url");
		serviceOrg.setRegions("Nepal, Mexico");
		serviceOrg.setPhoto("url");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(serviceOrg);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/service-org")
				.content(json)
				.contentType("application/json")
				.sessionAttr("username", "paigeCandace") //fake lgoin
		);
		Assert.assertTrue(serviceOrgs.count() == 1);



	}

	//findAllGET
	@Test
	public void test4ServiceOrg() throws Exception {
		MvcResult result = mockMvc.perform(
			MockMvcRequestBuilders.get("/service-org")
	).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String responseStr = response.getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		ArrayList responseArray = mapper.readValue(responseStr, ArrayList.class);
		Assert.assertTrue(responseArray.size() > 0);
	}

	//update ServiceOrg
	@Test
	public void test5ServiceOrg() throws Exception {
//		User user = new User();
//		user.setUsername("paigeCandace");
//		user.setPassword("ironyard");
//		user.setEmail("pc@ironyard.com");
//		user.setUserType(User.UserType.Admin);
		ServiceOrg serviceOrg = new ServiceOrg();
		serviceOrg.setId(1);
		serviceOrg.setName("AWB");
		serviceOrg.setDescription("international aid");
		serviceOrg.setDonateLink("url");
		serviceOrg.setVolunteerLink("url");
		serviceOrg.setRegions("Guatemala");
		serviceOrg.setPhoto("url");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(serviceOrg);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/service-org")
						.content(json)
						.contentType("application/json")
						.sessionAttr("username", "paigeCandace")
		);
		Assert.assertTrue(serviceOrgs.findOne(1).getRegions().equals("Guatemala"));

	}

	//delete ServiceOrg
	@Test
	public void test6ServiceOrg() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/service-org/1")
				.sessionAttr("username", "paigeCandace")
		);
		Assert.assertTrue(serviceOrgs.count() == 0);
	}

	//create VolunteerProfile
	@Test
	public void test7VolunteerProfile() {
		VolunteerProfile volunteerProfile = new VolunteerProfile();
		volunteerProfile.setName("Paige");
		volunteerProfile.setOrganization("AWB");
		volunteerProfile.setCountry("Nepal");
		volunteerProfile.setDescription("Great");
		volunteerProfile.setPhoto("url");

		ObjectMapper mapper = new ObjectMapper();


	}



	@Test
	public void test9logout() throws Exception {
		ResultActions ra = mockMvc.perform(
				MockMvcRequestBuilders.post("/logout")
		);
		MvcResult result = ra.andReturn();
		MockHttpServletRequest request = result.getRequest();
		HttpSession session = request.getSession();
		Assert.assertTrue(session.getAttribute("username") == null);


	}

}

package com.onPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onPoint.entities.User;
import com.onPoint.services.CommentRepository;
import com.onPoint.services.ServiceOrgRepository;
import com.onPoint.services.UserRepository;
import com.onPoint.services.VolunteerProfileRepository;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
		user.setPassword("123");
		user.setEmail("h@g.com");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/register")
				.content(json)
				.contentType("application/json")
		);
		Assert.assertTrue(users.count() == 1);


	}

}

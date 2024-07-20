package com.example.springboot;

import com.example.springboot.controller.UserController;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private UserService userService;
	
	@Test
	public void GetAllUsers() throws Exception {
		User user1 = new User(1L, "John Doe", "johndoe@example.com");
		User user2 = new User(2L, "Jane Doe", "janedoe@example.com");
		
		given(userService.findAll()).willReturn(Arrays.asList(user1, user2));
		
		mockMvc.perform(get("/api"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name").value("John Doe"))
			.andExpect(jsonPath("$[1].name").value("Jane Doe"));
			
	}
	
	@Test
	public void Save() throws Exception {
		User mockNewUser = new User(1L, "John Doe", "johndoe@example.com");
		
		given(userService.save(any(User.class))).willReturn(mockNewUser);
		
		mockMvc.perform(post("/api")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"id\": \"1\",\"name\":\"John Doe\", \"email\":\"john.doe@example.com\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("John Doe"));
		
	}
	
	@Test
	public void DeleteAll() throws Exception {
		doNothing().when(userService).deleteAll();
		
		mockMvc.perform(delete("/api"))
			.andExpect(status().isOk());
		
	}
}

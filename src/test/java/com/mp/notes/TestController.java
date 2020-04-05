package com.mp.notes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestController extends NotesApplicationTests{
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testNotesGet()throws Exception{
		mockMvc.perform(get("api/notes")).andExpect(status().isOk()).
		andExpect(content().contentType("application/json;charset=UTF-8")).
		andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.content").value("notes")).
		andExpect(jsonPath("$.title").value("Des")).andExpect(jsonPath("$.createdAt").value("2020-12-15"))
		.andExpect(jsonPath("$.lastModifiedAt").value("2020-12-17"));
	}
}


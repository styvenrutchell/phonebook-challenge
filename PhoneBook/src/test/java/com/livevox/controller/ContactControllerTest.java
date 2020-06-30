package com.livevox.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.livevox.dto.ContactDTO;
import com.livevox.service.ContactService;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ContactService contactService;
	
	@Test
	public void testCreateContact() throws Exception {
		when(contactService.createContact(any(ContactDTO.class))).thenReturn(true);
		mvc.perform(post("/contacts"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testSearchContacts() throws Exception {
		ContactDTO contact = new ContactDTO();
		contact.setId(1);
		contact.setName("test");
		contact.setLastName("test");
		contact.setPhoneNumber("11111");
		when(contactService.getAllContact()).thenReturn(Arrays.asList(contact));
		mvc.perform(get("/contacts"))
		.andExpect(status().isOk());
	}

}

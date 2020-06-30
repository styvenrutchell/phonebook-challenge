package com.livevox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.livevox.dto.ContactDTO;
import com.livevox.exceptions.MissingFieldException;
import com.livevox.model.Contact;
import com.livevox.repository.ContactRepository;

@SpringBootTest
public class ContactServiceTest {
	
	@MockBean
	ContactRepository contactRepository;
	
	@Autowired
	ContactService contactService;
	
	@Test
	public void testGetAllContacts() {
		when(contactRepository.findAll()).thenReturn(getContactsTest());
		List<ContactDTO> contacts = contactService.getAllContact();
		assertEquals(2, contacts.size());
		assertEquals(1, contacts.get(0).getId());
		assertEquals("test2", contacts.get(1).getName());
	}
	
	@Test
	public void testSearchContactByKeyword() {
		when(contactRepository.search(anyString())).thenReturn((List<Contact>) getContactsTest());
		List<ContactDTO> contacts = contactService.searchContactByKeyword("test");
		assertEquals(2, contacts.size());
		assertEquals(1, contacts.get(0).getId());
		assertEquals("test2", contacts.get(1).getName());
	}
	
	@Test
	public void testCreateContact() {
		when(contactRepository.save(any(Contact.class))).thenReturn(getContactTest());
		boolean contactSaved = contactService.createContact(new ContactDTO(1,"nombre","apellido","1111"));
		assertTrue(contactSaved);
	}
	
	@Test
	public void testCreateContactFailed() {
		Exception exception = assertThrows(MissingFieldException.class, () -> {
			contactService.createContact(new ContactDTO());
		});
		
		String expectedMessage = "Name field cannot be null";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	private Contact getContactTest() {
		return new Contact(1L,"TestCreated","LastName","1111");
	}
	
	private Iterable<Contact> getContactsTest() {
		Contact contact1 = new Contact(1,"test1","test1","11111");
		Contact contact2 = new Contact(2,"test2","test2","22222");

		return Arrays.asList(contact1,contact2);
	}

}

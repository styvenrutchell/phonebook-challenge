package com.livevox.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.livevox.model.Contact;

@DataJpaTest
public class ContactRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Test
	public void testFindAllContacts() {
		List<Contact>contactListTest = getContactsForTest();
		contactListTest.forEach(c->entityManager.persist(c));
		entityManager.flush();
		Iterable<Contact> allContacts = contactRepository.findAll();
		assertEquals(3, StreamSupport.stream(allContacts.spliterator(), false).count());
		assertEquals("Test1Name", allContacts.iterator().next().getName());		
	}
	
	@Test
	public void testSearchByKewordShouldReturnTest2Only() {
		List<Contact>contactListTest = getContactsForTest();
		contactListTest.forEach(c->entityManager.persist(c));
		entityManager.flush();
		
		List<Contact> contactsReturned = contactRepository.search("2");
		assertEquals(1,contactsReturned.size());
		assertEquals("Test2Name", contactsReturned.get(0).getName());
	}
	
	@Test
	public void testSearchByKeywordShouldReturnAll() {
		List<Contact>contactListTest = getContactsForTest();
		contactListTest.forEach(c->entityManager.persist(c));
		entityManager.flush();
		
		List<Contact> contactsReturned = contactRepository.search("t");
		assertEquals(3,contactsReturned.size());
		assertEquals("Test3Name", contactsReturned.get(2).getName());
		
	}
	
	@Test
	public void testSearchByKeywordShouldReturnEmpty() {
		List<Contact>contactListTest = getContactsForTest();
		contactListTest.forEach(c->entityManager.persist(c));
		entityManager.flush();		
		List<Contact> contactsReturned = contactRepository.search("r");
		assertTrue(contactsReturned.isEmpty());
	}
	
	@Test
	public void testCreateContact() {
		Contact contact = new Contact();
		contact.setName("TestCreateName");
		contact.setLastName("TestCreateLastName");
		contact.setPhoneNumber("123456");
		
		Contact contactSaved = contactRepository.save(contact);
		assertTrue(contactSaved.getId()>0);
		assertEquals("TestCreateName", contactSaved.getName());
		assertEquals("TestCreateLastName",contactSaved.getLastName());
		assertEquals("123456", contactSaved.getPhoneNumber());
	}
	
	
	private List<Contact> getContactsForTest(){
		Contact contact1 = new Contact();
		contact1.setName("Test1Name");
		contact1.setLastName("Test1LastName");
		contact1.setPhoneNumber("1111");
		
		Contact contact2 = new Contact();
		contact2.setName("Test2Name");
		contact2.setLastName("Test2LastName");
		contact2.setPhoneNumber("1111");
		
		Contact contact3 = new Contact();
		contact3.setName("Test3Name");
		contact3.setLastName("Test3LastName");
		contact3.setPhoneNumber("3333");
		return Arrays.asList(contact1,contact2,contact3);
		
	}

}

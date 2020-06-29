package com.livevox.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.livevox.dto.ContactDTO;
import com.livevox.model.Contact;
import com.livevox.repository.ContactRepository;


@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public boolean createContact(ContactDTO contactDto) {
		if(validateContact(contactDto)) {
			Contact contact = contactFromDTO(contactDto);
			contactRepository.save(contact);
			return true;
		}
		
		return false;
		
	}

	
	@Override
	public List<ContactDTO> getAllContact() {
		Iterable<Contact> allContacts = contactRepository.findAll();
		return toDTO(allContacts);
	}
	
	@Override
	public List<ContactDTO> searchContactByKeyword(String keyword) {
		List<Contact> filteredContacts = contactRepository.search(keyword);
		return filteredContacts.stream().map(this::mapContactToDto)
				.collect(Collectors.toList());
	}
	
	private List<ContactDTO> toDTO(Iterable<Contact> contactList) {
		List<ContactDTO> allContacts = StreamSupport.stream(contactList.spliterator(), false)
					.map(this::mapContactToDto)
					.collect(Collectors.toList());
		
		return allContacts;
	}

	private Contact contactFromDTO(ContactDTO contactDto) {
			Contact contact = new Contact();
			contact.setName(contactDto.getName());
			contact.setLastName(contactDto.getLastName());
			contact.setPhoneNumber(contactDto.getPhoneNumber());
			return contact;
	}
	
	private boolean validateContact(ContactDTO contactDto) {
		if(StringUtils.isEmpty(contactDto.getName())) {
			return false;
		}
		return true;
		
	}


	private ContactDTO mapContactToDto (Contact contact) {
		return new ContactDTO(contact.getId(),contact.getName(),contact.getLastName(),contact.getPhoneNumber());
	}


	
}

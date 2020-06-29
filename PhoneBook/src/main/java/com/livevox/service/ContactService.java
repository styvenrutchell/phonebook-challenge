package com.livevox.service;

import java.util.List;

import com.livevox.dto.ContactDTO;

public interface ContactService {
	boolean createContact(ContactDTO contact);
	List<ContactDTO> getAllContact();
	List<ContactDTO> searchContactByKeyword(String keyword);

}

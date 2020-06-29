package com.livevox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.livevox.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	
	@Query("SELECT c FROM Contact c WHERE c.name LIKE %?1%"
			+" OR c.lastName LIKE %?1%"
			+" OR c.phoneNumber LIKE %?1%")	
	public List<Contact> search (String keyword);

}

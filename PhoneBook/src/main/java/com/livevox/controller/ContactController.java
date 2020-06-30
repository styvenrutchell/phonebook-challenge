package com.livevox.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.livevox.dto.ContactDTO;
import com.livevox.service.ContactService;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;

	/**
	 * Loads the index page
	 */
	@RequestMapping(value="/")
	public String homePage(HttpServletRequest request) {
		request.setAttribute("contacts", contactService.getAllContact());		
		return "index";
	}
	
	@GetMapping("/contacts")
	public String searchContacts(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		if(StringUtils.isEmpty(keyword)) {
			request.setAttribute("contacts", contactService.getAllContact());	
		} else {
			request.setAttribute("contacts", contactService.searchContactByKeyword(keyword));
		}

			
		return "index";
	}
	
	@PostMapping("/contacts")
	public String createContact(@ModelAttribute ContactDTO contactDto, HttpServletRequest request) throws Exception {
		if(contactService.createContact(contactDto)) {
			request.setAttribute("contacts", contactService.getAllContact());
			return "index";
		}
		
		return null;

	}
	
}

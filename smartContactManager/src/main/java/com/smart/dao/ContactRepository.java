package com.smart.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.Contact;
import com.smart.entities.User;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	//pagination...
	
	@Query("from Contact as c where c.user.id =:userId")
	//current-page Page
	//Contact Per Page - 5
	public Page<Contact> findContactsByUser(@Param("userId")int userId, Pageable pePageable);
	
	//search ke method hai
	//serach hum kare ge jo banda login hai us ke hum contact search kare ge. Esa nahi hoga wanda login nahi hai hum usse search kar rahe hai nahi hoga.
	
	public List<Contact> findByNameContainingAndUser(String name, User user);
	
}

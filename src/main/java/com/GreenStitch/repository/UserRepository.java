package com.GreenStitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GreenStitch.model.UserData;

@Repository
public interface UserRepository  extends JpaRepository<UserData, Integer>{

	UserData findByEmail(String username);
   
}

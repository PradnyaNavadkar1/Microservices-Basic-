package com.demo.userservice.reposities;

import com.demo.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,String> {


}

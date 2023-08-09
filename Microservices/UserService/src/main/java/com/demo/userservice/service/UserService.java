package com.demo.userservice.service;

import com.demo.userservice.entities.User;

import java.util.List;

public interface UserService {

    //Create user
    User saveUser(User user);
    //getallUser
    List<User> getAllUser();
    //get single user of given userId
    User getUserId(String userId);

}

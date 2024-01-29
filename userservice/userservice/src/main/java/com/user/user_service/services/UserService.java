package com.user.user_service.services;

import com.user.user_service.entites.User;

import java.util.List;

public interface UserService {

    //user operations
    //    create user
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user
    User getUser(String userId);


}

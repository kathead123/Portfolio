package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int id);

    User findByUsername(String username);
    //there is already a method here that allows you to find by username OR below to find
    //id by username
    //just include Principal in your argument list & then you can pull the token off

    int findIdByUsername(String username);

    boolean create(String username, String password);


}

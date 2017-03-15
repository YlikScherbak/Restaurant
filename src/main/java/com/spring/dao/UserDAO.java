package com.spring.dao;


import com.spring.model.User;

import java.util.List;

public interface UserDAO extends MyDAO<User, Long> {

    User findUserByName(String name);

    List<User> getAllWaiter();
}

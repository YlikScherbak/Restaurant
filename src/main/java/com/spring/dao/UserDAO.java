package com.spring.dao;


import com.spring.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends MyDAO<User, Long> {

    Optional<User> findUserByName(String name);

    List<User> getAllWaiter();
}

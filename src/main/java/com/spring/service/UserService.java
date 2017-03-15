package com.spring.service;


import com.spring.dto.adminDTO.UserDTO;
import com.spring.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    void saveUser(User user);

    List<UserDTO> getAllUsers();

    void enableDisableWaiter(Long id);

    User findWaiterById(Long id);

    void editWaiter(User user);
}

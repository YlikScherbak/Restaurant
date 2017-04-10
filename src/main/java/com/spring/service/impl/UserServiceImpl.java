package com.spring.service.impl;

import com.spring.dao.UserDAO;
import com.spring.dto.adminDTO.UserDTO;
import com.spring.exception.DAOException;
import com.spring.exception.InsufficientPermissionsException;
import com.spring.model.User;
import com.spring.model.enums.Role;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        if (userDAO.findUserByName(user.getUsername()).isPresent()){
            throw new DAOException("Duplicate user name");
        }
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Role.ROLE_WAITER);
        userDAO.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDAO.findUserByName(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        userDAO.getAllWaiter().forEach(user -> list.add(new UserDTO(user)));
        return list;
    }

    @Override
    @Transactional
    public void enableDisableWaiter(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(Objects.equals(user.getId(), id)){
            throw new InsufficientPermissionsException("You cac not disable administrator");
        }

        User waiter = userDAO.findById(id).orElseThrow(() -> new DAOException("The waiter with such id does not exist"));
        if (waiter.isEnabled()){
            waiter.setEnabled(false);
        }else {
            waiter.setEnabled(true);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findWaiterById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new DAOException("Such waiter does not exist"));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void editWaiter(User user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Role.ROLE_WAITER);
        userDAO.merge(user);
    }
}

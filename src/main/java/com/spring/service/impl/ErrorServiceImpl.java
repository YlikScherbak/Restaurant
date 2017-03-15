package com.spring.service.impl;

import com.spring.dao.ErrorDAO;
import com.spring.dto.waitresDTO.ErrorDTO;
import com.spring.model.Error;
import com.spring.model.User;
import com.spring.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ErrorServiceImpl implements ErrorService {

    private final ErrorDAO errorDAO;

    @Autowired
    public ErrorServiceImpl(ErrorDAO errorDAO) {
        this.errorDAO = errorDAO;
    }

    @Override
    @Transactional
    public void saveError(ErrorDTO errorDTO, Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Error error = new Error();
        error.setMessage(errorDTO.getMessage());
        error.setOrderId(id);
        error.setWaiter(user.getUsername());
        errorDAO.save(error);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Error> getErrors() {
        return errorDAO.findAll();
    }

    @Override
    @Transactional
    public void deleteError(Long id) {
        Error error = errorDAO.findReferenceById(id);
        errorDAO.remove(error);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkActiveErrors() {
        return errorDAO.getCount();
    }
}

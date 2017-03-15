package com.spring.dao.impl;

import com.spring.dao.ErrorDAO;
import com.spring.model.Error;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorDAOImpl extends MyDAOImpl<Error, Long> implements ErrorDAO {

    public ErrorDAOImpl() {
        super(Error.class);
    }

}

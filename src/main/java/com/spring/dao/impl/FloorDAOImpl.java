package com.spring.dao.impl;

import com.spring.dao.FloorDAO;
import com.spring.model.Floor;
import org.springframework.stereotype.Repository;

@Repository
public class FloorDAOImpl extends MyDAOImpl<Floor, Long> implements FloorDAO {

    public FloorDAOImpl() {
        super(Floor.class);
    }
}

package com.spring.dao.impl;


import com.spring.dao.TableDAO;
import com.spring.model.Table;
import org.springframework.stereotype.Repository;

@Repository
public class TableDAOImpl extends MyDAOImpl<Table, Integer> implements TableDAO {

    public TableDAOImpl() {
        super(Table.class);
    }
}

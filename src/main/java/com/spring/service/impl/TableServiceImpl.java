package com.spring.service.impl;

import com.spring.dao.FloorDAO;
import com.spring.dto.waitresDTO.FloorDTO;
import com.spring.dto.waitresDTO.TablesDTO;
import com.spring.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableServiceImpl implements TableService {

    private final FloorDAO floorDAO;

    @Autowired
    public TableServiceImpl(FloorDAO floorDAO) {
        this.floorDAO = floorDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FloorDTO> getAllTables() {
        List<FloorDTO> list = new ArrayList<>();
        floorDAO.findAll().forEach(floor ->
                list.add(new FloorDTO(floor.getFloorName(), floor.getTables()
                        .stream().map(TablesDTO::new).collect(Collectors.toList()), floor.getId())));
        return list;
    }

}

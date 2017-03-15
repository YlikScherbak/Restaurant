package com.spring.service;


import com.spring.dto.waitresDTO.FloorDTO;

import java.util.List;

public interface TableService {

    List<FloorDTO> getAllTables();
}

package com.spring.service;


import com.spring.dto.waitresDTO.MenuDTO;

import java.util.List;

public interface MenuService {

    List<MenuDTO> getMenu();
}

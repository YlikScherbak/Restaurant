package com.spring.service.impl;

import com.spring.dao.MenuCategoryDAO;
import com.spring.dto.waitresDTO.MenuDTO;
import com.spring.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{

    private final MenuCategoryDAO menuCategoryDAO;

    @Autowired
    public MenuServiceImpl(MenuCategoryDAO menuCategoryDAO) {
        this.menuCategoryDAO = menuCategoryDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> getMenu() {
        List<MenuDTO> list = new ArrayList<>();

        menuCategoryDAO.findAll().forEach(menuCategory ->
                list.add(new MenuDTO(menuCategory)));
        return list;
    }


}

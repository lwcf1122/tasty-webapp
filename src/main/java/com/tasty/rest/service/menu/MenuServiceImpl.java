package com.tasty.rest.service.menu;

import com.tasty.rest.dao.menu.MenuDAO;
import com.tasty.rest.entity.MenuDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by flao on 5/19/15.
 */
@Transactional
@Service
public class MenuServiceImpl implements MenuService {

    @Setter
    @Autowired
    private MenuDAO menuDAO;

    public MenuDO findById(Integer id) throws Exception {
        return menuDAO.find(id);
    }
}

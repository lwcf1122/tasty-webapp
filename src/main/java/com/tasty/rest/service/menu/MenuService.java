package com.tasty.rest.service.menu;

import com.tasty.rest.entity.MenuDO;

import org.springframework.stereotype.Service;

/**
 * Created by flao on 5/19/15.
 */
public interface MenuService {
    public MenuDO findById(Integer id) throws Exception;
}

package com.tasty.rest.service.menu;

import com.tasty.rest.dto.Menu;
import com.tasty.rest.entity.MenuDO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by flao on 5/19/15.
 */
public interface MenuService {
    public Menu findById(Integer id) throws Exception;

    public Map<String, List<Menu>> findAll() throws Exception;

}

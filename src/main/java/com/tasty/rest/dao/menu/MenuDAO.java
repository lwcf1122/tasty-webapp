package com.tasty.rest.dao.menu;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.tasty.rest.entity.MenuDO;

import org.springframework.stereotype.Repository;

/**
 * Created by flao on 5/19/15.
 */
@Repository
public interface MenuDAO extends GenericDAO<MenuDO, Integer> {

    public void delete(Integer id) throws Exception;
}

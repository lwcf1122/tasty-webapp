package com.tasty.rest.resource;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.tasty.rest.dao.menu.MenuDAO;
import com.tasty.rest.dao.menu.MenuDAOImp;
import com.tasty.rest.entity.MenuDO;
import com.tasty.rest.service.menu.MenuService;
import com.tasty.rest.service.menu.MenuServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by flao on 5/16/15.
 */
@Resource
@Path("/tasty")
public class SampleResource {

    @Autowired
    private MenuService menuService;

    public MenuService getMenuService() { return menuService; }

    @GET
    @Produces("text/plain")
    @Path("/app")
    public String get() throws Exception {
        MenuDO menuDO = menuService.findById(1);
        return "welcome to Tasty! " + menuDO.toString();
    }
}

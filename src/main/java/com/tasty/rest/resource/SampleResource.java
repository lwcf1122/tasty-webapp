package com.tasty.rest.resource;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.tasty.rest.dao.menu.MenuDAO;
import com.tasty.rest.dao.menu.MenuDAOImp;
import com.tasty.rest.dto.Menu;
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
@Path("/")
public class SampleResource {

    @GET
    @Produces("text/plain")
    public String get() throws Exception {
        return "welcome to Tasty! ";
    }
}

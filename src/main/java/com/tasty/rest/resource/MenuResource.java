package com.tasty.rest.resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tasty.rest.dto.Menu;
import com.tasty.rest.service.menu.MenuService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by flao on 5/20/15.
 */
@Slf4j
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Resource
@Path("menus")
public class MenuResource {

    @Autowired
    private MenuService menuService;

    @GET
    @JacksonFeatures(serializationEnable = SerializationFeature.WRAP_ROOT_VALUE,
                     deserializationEnable = DeserializationFeature.UNWRAP_ROOT_VALUE)
    @Path("{id}")
    public Menu getById(@PathParam("id") Integer id) throws Exception {
        return menuService.findById(id);
    }

    @GET
    public Map<String, List<Menu>> getAll() throws Exception {
        return menuService.findAll();
    }

    @POST
    @JacksonFeatures(serializationEnable = SerializationFeature.WRAP_ROOT_VALUE,
                     deserializationEnable = DeserializationFeature.UNWRAP_ROOT_VALUE)
    public Menu create(Menu menuDTO) throws Exception {
        return menuService.add(menuDTO);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") Integer itemId, Menu menuDTO) throws Exception {
        menuService.update(itemId, menuDTO);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer itemId) throws Exception {
        menuService.delete(itemId);
    }
}

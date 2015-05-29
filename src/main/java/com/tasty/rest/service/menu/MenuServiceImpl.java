package com.tasty.rest.service.menu;

import com.tasty.rest.common.enums.ServiceOperation;
import com.tasty.rest.dao.menu.MenuDAO;
import com.tasty.rest.dto.Menu;
import com.tasty.rest.entity.MenuDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import lombok.Setter;

/**
 * Created by flao on 5/19/15.
 */
@Transactional
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    @Setter
    private MenuDAO menuDAO;

    @Autowired
    @Setter
    private MenuMapper mapper;

    @Override
    public Menu findById(Integer id) throws Exception {
        MenuDO menuDO = menuDAO.find(id);
        if (menuDO == null) throw new WebApplicationException(Response.Status.NOT_FOUND); //TODO move to abstract/base
        Menu menuDTO = mapper.mapToDTO(menuDO, ServiceOperation.GET);
        return menuDTO;
    }

    @Override
    public Map<String, List<Menu>> findAll() throws Exception {
        List<MenuDO> menuDOList = menuDAO.findAll();
        List<Menu> menuDTOList = mapper.mapToDTO(menuDOList, ServiceOperation.GET);
        Map<String, List<Menu>> map = new HashMap<String, List<Menu>>();
        map.put(getPluralizeJsonRootName(constructJsonRoot(menuDTOList.get(0).getClass())), menuDTOList); //TODO move to abstract/base
        return map;
    }

    @Override
    public Menu add(Menu menuDTO) throws Exception {
        //validate DTO
        if (menuDTO == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        menuDTO.setId(null);
        MenuDO menuDO = mapper.mapToDO(menuDTO, ServiceOperation.ADD);
        menuDO = menuDAO.save(menuDO);
        return mapper.mapToDTO(menuDO, ServiceOperation.ADD);
    }

    @Override
    public void update(Integer id, Menu menuDTO) throws Exception {
        if (menuDTO == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        menuDTO.setId(id);
        //validate DTO
        MenuDO oldDo = menuDAO.find(id);
        if (oldDo == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        MenuDO toUpdateDO = mapper.mapToDO(menuDTO, ServiceOperation.UPDATE);
        MenuDO updatedDO = menuDAO.merge(toUpdateDO);
        if (updatedDO == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
    }

    @Override
    public void delete(Integer id) throws Exception {
        if (id == null || id <= 0) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        if (menuDAO.find(id) == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        menuDAO.delete(id);
    }

    private String getPluralizeJsonRootName(String rootName) {
        return rootName + "s";
    }

    private String constructJsonRoot(Class dtoClass) {
        String className = dtoClass.getSimpleName();
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }
}

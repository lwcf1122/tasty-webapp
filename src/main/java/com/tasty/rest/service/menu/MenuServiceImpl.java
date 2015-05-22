package com.tasty.rest.service.menu;

import com.tasty.rest.dao.menu.MenuDAO;
import com.tasty.rest.dto.Menu;
import com.tasty.rest.entity.MenuDO;

import org.apache.commons.collections.CollectionUtils;
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
        Menu menuDTO = new Menu();
        menuDTO = mapper.mapDOToDTO(menuDO, menuDTO);
        return menuDTO;
    }

    @Override
    public Map<String, List<Menu>> findAll() throws Exception {
        List<MenuDO> menuDOList = menuDAO.findAll();
        List<Menu> menuDTOList = mapper.mapDOToDTO(menuDAO.findAll());
        Map<String, List<Menu>> map = new HashMap<String, List<Menu>>();
        map.put(getPluralizeJsonRootName(constructJsonRoot(menuDTOList.get(0).getClass())), menuDTOList); //TODO move to abstract/base
        return map;
    }

    private String getPluralizeJsonRootName(String rootName) {
        return rootName + "s";
    }

    private String constructJsonRoot(Class dtoClass) {
        String className = dtoClass.getSimpleName();
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }
}

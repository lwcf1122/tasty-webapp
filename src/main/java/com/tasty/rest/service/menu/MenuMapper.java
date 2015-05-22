package com.tasty.rest.service.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasty.rest.dto.Menu;
import com.tasty.rest.entity.MenuDO;

//import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by flao on 5/20/15.
 */
@Component
public class MenuMapper {

    public Menu mapDOToDTO(MenuDO menuDO, Menu menuDTO) throws Exception {
//        BeanUtils.copyProperties(menuDTO, menuDO);
        BeanUtils.copyProperties(menuDO, menuDTO);
        return serializer(menuDTO);
    }

    public List<Menu> mapDOToDTO(List<MenuDO> menuDOList) throws Exception {
        if (CollectionUtils.isEmpty(menuDOList)) return Collections.emptyList();
        List<Menu> menuList = new ArrayList<Menu>(menuDOList.size());
        Iterator<MenuDO> menuDOListItr = menuDOList.iterator();
        while (menuDOListItr.hasNext()) {
            MenuDO menuDO = menuDOListItr.next();
            Menu menuDTO = new Menu();
            BeanUtils.copyProperties(menuDO, menuDTO);
            menuDTO = serializer(menuDTO);
            menuList.add(menuDTO);
        }
        return menuList;
    }

    private Menu serializer(Menu menuDTO) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        String strOutput;
        strOutput = objectMapper.writeValueAsString(menuDTO);
        Menu newMenuDTO = objectMapper.readValue(strOutput, menuDTO.getClass());
        return newMenuDTO;
    }
}

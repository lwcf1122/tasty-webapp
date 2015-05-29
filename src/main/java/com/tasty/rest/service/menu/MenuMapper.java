package com.tasty.rest.service.menu;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasty.rest.common.View;
import com.tasty.rest.common.enums.ServiceOperation;
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

    public MenuDO mapToDO(Menu menuDTO, ServiceOperation serviceOperation) {
        MenuDO menuDO = new MenuDO();
        BeanUtils.copyProperties(menuDTO, menuDO);
        return menuDO;
    }

    public Menu mapToDTO(MenuDO menuDO, ServiceOperation serviceOperation) throws Exception {
        Menu menuDTO = new Menu();
//        BeanUtils.copyProperties(menuDTO, menuDO);
        BeanUtils.copyProperties(menuDO, menuDTO);
        return serializer(menuDTO, serviceOperation);
    }

    public List<Menu> mapToDTO(List<MenuDO> menuDOList, ServiceOperation serviceOperation) throws Exception {
        if (CollectionUtils.isEmpty(menuDOList)) return Collections.emptyList();
        List<Menu> menuList = new ArrayList<Menu>(menuDOList.size());
        Iterator<MenuDO> menuDOListItr = menuDOList.iterator();
        while (menuDOListItr.hasNext()) {
            MenuDO menuDO = menuDOListItr.next();
            Menu menuDTO = new Menu();
            BeanUtils.copyProperties(menuDO, menuDTO);
            menuDTO = serializer(menuDTO, serviceOperation);
            menuList.add(menuDTO);
        }
        return menuList;
    }

    private Menu serializer(Menu menuDTO, ServiceOperation serviceOperation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        String strOutput;
        if (serviceOperation != null) {
            if (serviceOperation.equals(ServiceOperation.ADD))
                strOutput = objectMapper.writerWithView(View.PostResponse.class).writeValueAsString(menuDTO);
            else if (serviceOperation.equals(ServiceOperation.GET))
                strOutput = objectMapper.writerWithView(View.GetResponse.class).writeValueAsString(menuDTO);
            else
                strOutput = objectMapper.writeValueAsString(menuDTO);
        } else {
            strOutput = objectMapper.writeValueAsString(menuDTO);
        }
        Menu newMenuDTO = objectMapper.readValue(strOutput, menuDTO.getClass());
        return newMenuDTO;
    }
}

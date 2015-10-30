/*************************************************************************
 * Copyright (c) Metabiota Incorporated - All Rights Reserved
 * ------------------------------------------------------------------------
 * This material is proprietary to Metabiota Incorporated. The
 * intellectual and technical concepts contained herein are proprietary
 * to Metabiota Incorporated. Reproduction or distribution of this
 * material, in whole or in part, is strictly forbidden unless prior
 * written permission is obtained from Metabiota Incorporated.
 * ***********************************************************************
 * <p/>
 * Created by WLao on 10/29/15.
 */
package com.recipe.rest.service.user;

import com.recipe.rest.common.enums.ServiceOperation;
import com.recipe.rest.dao.user.UserDAO;
import com.recipe.rest.dto.User;
import com.recipe.rest.dto.User;
import com.recipe.rest.entity.UserDO;
import com.recipe.rest.service.user.UserMapper;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Setter
    private UserDAO userDAO;

    @Autowired
    @Setter
    private UserMapper mapper;

    @Override
    public User findById(Integer id) throws Exception {
        UserDO userDO = userDAO.find(id);
        if (userDO == null) throw new WebApplicationException(Response.Status.NOT_FOUND); //TODO move to abstract/base
        User userDTO = mapper.mapToDTO(userDO, ServiceOperation.GET);
        return userDTO;
    }

    @Override
    public Map<String, List<User>> findAll() throws Exception {
        List<UserDO> userDOList = userDAO.findAll();
        List<User> userDTOList = mapper.mapToDTO(userDOList, ServiceOperation.GET);
        Map<String, List<User>> map = new HashMap<String, List<User>>();
        if (CollectionUtils.isEmpty(userDTOList)) return map;
        map.put(getPluralizeJsonRootName(constructJsonRoot(userDTOList.get(0).getClass())), userDTOList); //TODO move to abstract/base
        return map;
    }

    @Override
    public User add(User userDTO) throws Exception {
        //validate DTO
        if (userDTO == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        userDTO.setId(null);
        UserDO userDO = mapper.mapToDO(userDTO, ServiceOperation.ADD);
        userDO = userDAO.save(userDO);
        return mapper.mapToDTO(userDO, ServiceOperation.ADD);
    }

    @Override
    public void update(Integer id, User userDTO) throws Exception {
        if (userDTO == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        userDTO.setId(id);
        //validate DTO
        UserDO oldDo = userDAO.find(id);
        if (oldDo == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
        UserDO toUpdateDO = mapper.mapToDO(userDTO, ServiceOperation.UPDATE);
        UserDO updatedDO = userDAO.merge(toUpdateDO);
        if (updatedDO == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
    }

//    @Override
//    public void delete(Integer id) throws Exception {
//        if (id == null || id <= 0) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
//        if (userDAO.find(id) == null) throw new WebApplicationException(Response.Status.BAD_REQUEST); //TODO move to abstract/base
//        userDAO.delete(id);
//    }

    private String getPluralizeJsonRootName(String rootName) {
        return rootName + "s";
    }

    private String constructJsonRoot(Class dtoClass) {
        String className = dtoClass.getSimpleName();
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }
}

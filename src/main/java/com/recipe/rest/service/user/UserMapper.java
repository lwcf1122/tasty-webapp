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

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.rest.common.View;
import com.recipe.rest.common.enums.ServiceOperation;
import com.recipe.rest.dto.User;
import com.recipe.rest.entity.UserDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
public class UserMapper {

    public UserDO mapToDO(User userDTO, ServiceOperation serviceOperation) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        return userDO;
    }

    public User mapToDTO(UserDO userDO, ServiceOperation serviceOperation) throws Exception {
        User userDTO = new User();
//        BeanUtils.copyProperties(userDTO, userDO);
        BeanUtils.copyProperties(userDO, userDTO);
        return serializer(userDTO, serviceOperation);
    }

    public List<User> mapToDTO(List<UserDO> userDOList, ServiceOperation serviceOperation) throws Exception {
        if (CollectionUtils.isEmpty(userDOList)) return Collections.emptyList();
        List<User> userList = new ArrayList<User>(userDOList.size());
        Iterator<UserDO> userDOListItr = userDOList.iterator();
        while (userDOListItr.hasNext()) {
            UserDO userDO = userDOListItr.next();
            User userDTO = new User();
            BeanUtils.copyProperties(userDO, userDTO);
            userDTO = serializer(userDTO, serviceOperation);
            userList.add(userDTO);
        }
        return userList;
    }

    private User serializer(User userDTO, ServiceOperation serviceOperation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        String strOutput;
        if (serviceOperation != null) {
            if (serviceOperation.equals(ServiceOperation.ADD))
                strOutput = objectMapper.writerWithView(View.PostResponse.class).writeValueAsString(userDTO);
            else if (serviceOperation.equals(ServiceOperation.GET))
                strOutput = objectMapper.writerWithView(View.GetResponse.class).writeValueAsString(userDTO);
            else
                strOutput = objectMapper.writeValueAsString(userDTO);
        } else {
            strOutput = objectMapper.writeValueAsString(userDTO);
        }
        User newUserDTO = objectMapper.readValue(strOutput, userDTO.getClass());
        return newUserDTO;
    }
}

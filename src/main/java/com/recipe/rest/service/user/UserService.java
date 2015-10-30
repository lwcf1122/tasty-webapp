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

import com.recipe.rest.dto.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User findById(Integer id) throws Exception;

    public Map<String, List<User>> findAll() throws Exception;

    public User add(User userDTO) throws Exception;

    public void update(Integer id, User userDTO) throws Exception;

//    public void delete(Integer id) throws Exception;
}

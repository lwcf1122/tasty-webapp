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
package com.recipe.rest.dao.user;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.recipe.rest.entity.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends GenericDAO<UserDO, Integer> {

}

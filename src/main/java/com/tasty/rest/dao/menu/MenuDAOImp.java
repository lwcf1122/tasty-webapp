package com.tasty.rest.dao.menu;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.tasty.rest.entity.MenuDO;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by flao on 5/19/15.
 */
@Repository
public class MenuDAOImp extends GenericDAOImpl<MenuDO, Integer> implements MenuDAO {

    @Override
    @PersistenceContext(unitName="tastyRestPersistence")
    @Qualifier(value="entityManagerFactory")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }
}

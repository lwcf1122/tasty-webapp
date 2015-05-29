package com.tasty.rest.dao.menu;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import com.tasty.rest.entity.MenuDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    @Autowired
    @Qualifier(value="searchProcessor")
    public void setSearchProcessor(JPASearchProcessor searchProcessor) {
        super.setSearchProcessor(searchProcessor);
    }

    public void delete(Integer id) throws Exception {
        if (id == null) return;
        Query query = em().createQuery("UPDATE MenuDO m SET m.status = 0"
                                       + ", m.updatedAt = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
            new Date()) + "'"
                                       + " WHERE m.id = " + id);
        query.executeUpdate();
    }
}

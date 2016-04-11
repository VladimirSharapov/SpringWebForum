/**
 * This project is a simple web forum. I created it just to
 * demonstrate my programming skills to potential employers.
 *
 * Here is short description: ( for more detailed description please reade README.md or
 * go to https://github.com/VladimirSharapov/SpringWebForum )
 *
 * Front-end: jsp, bootstrap, jquery
 * Back-end: Spring, Hibernate
 * DB: MySQL and H2(for testing) were used while developing, but the project is database independent.
 *     Though it must be a relational DB.
 * Tools: git,maven,jenkins,nexus,liquibase.
 *
 * My LinkedIn profile: https://ru.linkedin.com/in/vladimir-sharapov-6075207
 */
package org.shv.webforum.service.common;

import org.shv.webforum.common.BaseEntity;
import org.shv.webforum.common.Crud;

import java.lang.reflect.ParameterizedType;


/**
 * @author Vladimir Sharapov
 */
public class BaseEntityServiceImpl<T extends BaseEntity, Y extends Crud> implements BaseEntityService<T> {

    /**
     * dao class that provides crud operations for entity of type T
     */
    Y dao;

    /**
     * This constructor can be used by subclasses to inject dao
     *
     * @param dao - dao class that provides crud operations for entity of type T
     */
    protected BaseEntityServiceImpl(Y dao) {
        this.dao = dao;
    }

    /**
     * Returns the dao set in constructor
     *
     * @return dao set in the constructor
     */
    protected Y getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(Long id) throws NotFoundException {
        if(id < 0) {
            throw new IllegalArgumentException(String.format("Id [%d] must be positive number",id));
        }

        T entity = (T) dao.get(id);
        if(entity == null) {
            throw new NotFoundException(String.format("Entity %s with id [%d] is not found. ",getEntityClass(),id));
        }
        return entity;
    }

    /**
     * Returns entity class with which service implementation works
     * @return entity class for service implementation
     */
    private Class<?> getEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }
}

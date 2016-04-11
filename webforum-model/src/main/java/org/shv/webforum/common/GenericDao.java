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
package org.shv.webforum.common;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


/**
 * Base class for access to the specified {@link org.shv.webforum.common.BaseEntity} objects.
 * Uses to load objects from database, save, update or delete them.
 * The implementation is based on the Hibernate.
 *
 * @author Vladimir Sharapov
 */
public class GenericDao<T extends BaseEntity> implements Crud<T> {

    /**
     * Hibernate SessionFactory
     */
    private final SessionFactory sessionFactory;

    /**
     * Type of entity
     */
    private final Class<T> type;

    /**
     * @param sessionFactory The SessionFactory.
     * @param type entity class
     */
    public GenericDao(SessionFactory sessionFactory, Class<T> type) {
        this.sessionFactory = sessionFactory;
        this.type = type;
    }

    /**
     * Get current Hibernate session.
     *
     * @return current Session
     */
    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Save or update entity.
     * <p>
     * This operation cascades to associated instances if the association
     * is mapped with cascade="save-update".
     * This operation will result in INSERT SQL statement (for saving new entity),
     * or in UPDATE statement for updating existing entity.
     * </p>
     * @param entity object to save
     */
    @Override
    public void saveOrUpdate(T entity) {
        session().saveOrUpdate(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        String deleteQuery = "delete " + type.getCanonicalName() + " e where e.id= :id";
        return session().createQuery(deleteQuery).setLong("id", id).executeUpdate() != 0;
    }

    /**
     * <p>Delete the entity by object reference.</p>
     * <p>This method deletes all cascaded references.</p>
     *
     * @param entity Entity to be deleted.
     */
    @Override
    public void delete(T entity) {
        session().delete(entity);
    }

    /**
     * Return the persistent instance of the parametrized entity class with the given identifier,
     * or null if there is no such persistent instance. (If the instance is already associated with the session,
     * return that instance. This method never returns an uninitialized instance.)
     * @param id The entity id.
     * @return A persistent instance or null.
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(Long id) {
        return (T) session().get(type, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExist(Long id) {
        return get(id) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void flush() {
        session().flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return type;
    }
}

package org.shv.webforum.common;

import org.shv.webforum.common.BaseEntity;

/**
 * Interface describing basic database access operations for domain objects.
 *
 * @param <T> The type of domain object.
 *
 * @author Vladimir Sharapov
 */
public interface Crud<T extends BaseEntity> {

    /**
     * Save or update entity.
     *
     * @param entity object to save
     */
    void saveOrUpdate(T entity);


    /**
     * <p>Delete the entity by id.</p>
     * <b>Please note - this method doesn't delete cascaded entities.</b>
     *
     * @param id The entity id.
     * @return {@code true} if entity deleted successfully
     */
    boolean delete(Long id);

    /**
     * <p>Delete the entity by object reference.</p>
     * <p>This method deletes all cascaded references.</p>
     *
     * @param entity Entity to be deleted.
     */
    void delete(T entity);

    /**
     * Get entity by id.
     *
     * @param id The entity id.
     * @return Persistent instance.
     */
    T get(Long id);

    /**
     * Check entity existence by id.
     *
     * @param id The entity id.
     * @return {@code true} if entity exist.
     */
    boolean isExist(Long id);

    /**
     * Make all changes flush to database.
     */
    void flush();
}

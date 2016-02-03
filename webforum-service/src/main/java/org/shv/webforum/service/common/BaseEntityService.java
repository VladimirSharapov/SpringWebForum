package org.shv.webforum.service.common;

import org.shv.webforum.service.common.NotFoundException;
import org.shv.webforum.common.BaseEntity;

/**
 * This is generic interface for services which would interact with database entities via DAO object.
 * This interface include all base method declaration which straightly based on database CRUD operations.
 *
 * @author Vladimir Sharapov
 */
public interface BaseEntityService<T extends BaseEntity> {

    /**
     * Get persistent object by id.
     *
     * @param id primary id of persistent object to find.
     *           If id < 0, then IllegalAgrumentEception will be thrown.
     * @return persistent object T or null if row with primary id  is absent.
     * @throws org.shv.webforum.service.common.NotFoundException when entity not found
     */
    T get(Long id) throws NotFoundException;
}

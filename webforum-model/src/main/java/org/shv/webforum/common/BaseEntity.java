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

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Base class for all entities in the project
 *
 * @author Vladimir Sharapov
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     *  We use enhanced-sequence strategy for id generation because this is
     *  database independent id generation strategy. This strategy can be used as for
     *  DB which support sequences, as for DB which do not support sequences.
     *  Generator is defined in org.shv.webforum.common.package-info.java
     */
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    /**
     * Get the primary id of the persistent object.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id for the persistent object.
     *
     * @param id id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Checking if entity has not yet been saved to database.
     *
     * @return true if entity ID does not equal to 0, else - false
     */
    public boolean isPersistent(){
        return getId() != 0;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if ( !(getClass().isInstance(obj)) ) {
            return false;
        }

        BaseEntity other = (BaseEntity) obj;
        return id.equals(other.id);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : -1;
    }

    public String toString() {
        return (id != null) ? id.toString() : null;
    }
}

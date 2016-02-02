package org.shv.webforum.common;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all entities in the project
 *
 * @author Vladimir Sharapov
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="IdOrGenerated")
    @GenericGenerator(name="IdOrGenerated", strategy="org.shv.webforum.common.UseIdOrGenerate")
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
        return id == other.id;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : -1;
    }
}

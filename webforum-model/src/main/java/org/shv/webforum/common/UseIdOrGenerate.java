package org.shv.webforum.common;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * Custom id generator for all entities inheriting from {@link org.shv.webforum.common.BaseEntity}.
 * We need this for unit test, because sometimes we need to create entity with predefined id.
 *
 * @author Vladimir Sharapov
 */
public class UseIdOrGenerate extends IdentityGenerator {


    @Override
    public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
        if (obj == null) throw new HibernateException(new NullPointerException());

        if ((((BaseEntity) obj).getId()) == null) {
            Serializable id = super.generate(session, obj);
            return id;
        } else {
            return ((BaseEntity) obj).getId();
        }
    }
}
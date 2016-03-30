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
package org.shv.webforum.model.dao.impl;

import org.shv.webforum.common.GenericDao;
import org.shv.webforum.model.dao.BranchDao;
import org.shv.webforum.model.entity.Branch;

import org.hibernate.SessionFactory;
import org.shv.webforum.model.entity.Post;

import java.util.List;


/**
 * DAO class for managing branch related operations and queries
 *
 * @author Vladimir Sharapov
 */
public class BranchDaoImpl extends GenericDao<Branch> implements BranchDao {

    public BranchDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Branch.class);
    }

}

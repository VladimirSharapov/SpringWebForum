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

import org.shv.webforum.common.Crud;
import org.shv.webforum.model.dao.BranchDao;
import org.shv.webforum.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.commons.lang.RandomStringUtils.random;


/**
 * @author Vladimir Sharapov
 */
public class BranchDaoImplTest extends BaseDaoImplTest<Branch> {

    @Autowired
    private BranchDao branchDao;

    @Override
    protected Crud getDao() {
        return branchDao;
    }

    @Override
    protected void changeEntity(Branch branch) {
        branch.setName(random(Branch.BRANCH_NAME_MAX_LENGTH));
    }

    //create domain objects to test constraint violation
    @Override
    protected void fillParameters() {
        entity().setName("  ");   // not blank
        entity().setName("");     // not empty
        entity().setName(null);   // not null
        entity().setName(random(Branch.BRANCH_NAME_MAX_LENGTH + 1));          // max length

        entity().setDescription(random(Branch.BRANCH_DESCRIPTION_MAX_LENGTH + 1));   //max length
    }
}

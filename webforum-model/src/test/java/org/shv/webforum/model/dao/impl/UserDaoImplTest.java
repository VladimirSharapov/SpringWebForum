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
import org.shv.webforum.model.dao.UserDao;
import org.shv.webforum.model.entity.User;
import org.shv.webforum.model.util.JpaEntityState;
import org.shv.webforum.model.util.PersistedObjectsFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.shv.webforum.model.entity.User.*;

public class UserDaoImplTest extends BaseDaoImplTest<User> {

    @Autowired
    private UserDao userDao;

    @Override
    protected Crud getDao() {
        return userDao;
    }

    @Override
    protected void changeEntity(User user) {
        user.setFirstName(random(User.USERNAME_FIRSTNAME_MAX_LENGTH));
    }

    @Test
    public void testFindUserByName() throws Exception {
        User user = PersistedObjectsFactory.createUser(JpaEntityState.DETACHED);

        User foundUser = userDao.findUserByName(user.getUsername());
        String foundUsername = foundUser.getUsername();

        assertEquals(user.getUsername(),foundUsername);
        assertReflectionEquals(user,foundUser);
    }

    //create domain objects to test constraint violation
    @Override
    protected void fillParameters() {
        entity().setFirstName(random(USERNAME_FIRSTNAME_MAX_LENGTH + 1));
        entity().setFirstName(random(USERNAME_FIRSTNAME_MIN_LENGTH - 1));

        entity().setLastName(random(USERNAME_LASTNAME_MAX_LENGTH + 1));
        entity().setLastName(random(USERNAME_LASTNAME_MIN_LENGTH - 1));

        entity().setUsername(random(USERNAME_MAX_LENGTH + 1));
        entity().setUsername(random(USERNAME_MIN_LENGTH - 1));
        entity().setUsername(null);

        entity().setPassword(random(PASSWORD_MAX_LENGTH+1));
        entity().setPassword(random(PASSWORD_MIN_LENGTH-1));
        entity().setPassword(null);
        entity().setPassword("");
        entity().setPassword("   ");
    }
}
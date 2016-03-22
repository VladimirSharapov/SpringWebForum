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
package org.shv.webforum.service.transactional;

import org.shv.webforum.model.dao.UserDao;
import org.shv.webforum.model.entity.User;
import org.shv.webforum.service.UserService;
import org.shv.webforum.service.common.BaseEntityServiceImpl;
import org.shv.webforum.service.common.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Service class that contains operations for manipulation with user data (reading, modifying etc)
 *
 * @author Vladimir Sharapov
 */
public class UserServiceImpl  extends BaseEntityServiceImpl<User, UserDao> implements UserService {

    protected UserServiceImpl(UserDao dao) {
        super(dao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getCurrentUser() {
         return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editUser(User user) {
        User editedUser = getDao().get(user.getId());

        editedUser.setFirstName(user.getFirstName());
        editedUser.setLastName(user.getLastName());
        editedUser.setEmail(user.getEmail());

        getDao().saveOrUpdate(editedUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePostCount() throws NotFoundException {
        User user = super.get(getCurrentUser().getId());

        user.setPostCount(user.getPostCount() + 1);

        getDao().saveOrUpdate(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User get(Long userId) throws NotFoundException {
        return super.get(userId);
    }
}

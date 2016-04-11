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

import org.shv.webforum.model.dao.PostDao;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.model.entity.User;
import org.shv.webforum.service.PostService;
import org.shv.webforum.service.UserService;
import org.shv.webforum.service.common.BaseEntityServiceImpl;
import org.shv.webforum.service.common.NotFoundException;
import org.shv.webforum.service.common.PAGE_SIZE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.joda.time.DateTime;


/**
 * @author Vladimir Sharapov
 */
public class PostServiceImpl extends BaseEntityServiceImpl<Post,PostDao> implements PostService {

    private UserService userService;

    protected PostServiceImpl(PostDao dao, UserService userService) {
        super(dao);
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    public Post get(Long id) throws NotFoundException {
        return super.get(id);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public Page<Post> getPostsInTopic(Topic topic, int page) {
        PageRequest pageRequest = new PageRequest(page, PAGE_SIZE.STANDARD.getSize());
        return getDao().getPostsInTopic(topic,pageRequest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPostToTopic(Post submittedPost, Topic topic) {
        User user = userService.getCurrentUser();

        submittedPost.setCreationDate(new DateTime());
        submittedPost.setUserCreated(user);
        submittedPost.setTopic(topic);

        topic.addPost(submittedPost);

        getDao().saveOrUpdate(submittedPost);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculatePostPage(Post post) {
        Topic topic = post.getTopic();
        int index = topic.getPosts().indexOf(post);

        return (index / PAGE_SIZE.STANDARD.getSize());
    }
}

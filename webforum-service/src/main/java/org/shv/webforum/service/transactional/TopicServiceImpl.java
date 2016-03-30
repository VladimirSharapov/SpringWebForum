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
import org.shv.webforum.model.dao.TopicDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.model.entity.User;
import org.shv.webforum.service.TopicService;
import org.shv.webforum.service.UserService;
import org.shv.webforum.service.common.BaseEntityServiceImpl;
import org.shv.webforum.service.common.NotFoundException;
import org.shv.webforum.service.common.PAGE_SIZE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.joda.time.DateTime;

import java.util.List;


/**
 * Implementation of {@link org.shv.webforum.service.TopicService}
 *
 * @author Vladimir Sharapov
 */
public class TopicServiceImpl extends BaseEntityServiceImpl<Topic, TopicDao> implements TopicService {

    private UserService userService;
    private PostDao postDao;

    protected TopicServiceImpl(TopicDao dao, PostDao postDao,UserService userService) {
        super(dao);
        this.postDao = postDao;
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Topic get(Long topicId) throws NotFoundException {
        return super.get(topicId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Topic> fetchTopics(Branch branch, int page) {
        PageRequest pageRequest = new PageRequest(page, PAGE_SIZE.STANDARD.getSize());
        return getDao().getTopicsInBranch(branch,pageRequest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Topic createTopicWithPost(Branch branch, Topic topic, Post post) {
        User user = userService.getCurrentUser();

        topic.setCreationDate(new DateTime());
        topic.setBranch(branch);
        topic.setTopicStarter(user);

        getDao().saveOrUpdate(topic);

        post.setCreationDate(new DateTime());
        post.setTopic(topic);
        post.setUserCreated(user);

        postDao.saveOrUpdate(post);

        return topic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Topic> getAllTopics() {
        return getDao().getAllTopics();
    }
}

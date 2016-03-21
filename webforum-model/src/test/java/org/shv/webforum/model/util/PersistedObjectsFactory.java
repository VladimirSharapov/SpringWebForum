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
package org.shv.webforum.model.util;

import org.hibernate.Session;
import org.joda.time.DateTime;
import org.shv.webforum.common.BaseEntity;
import org.shv.webforum.model.entity.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.RandomStringUtils.random;

/**
 * @author Vladimir Sharapov
 */
public class PersistedObjectsFactory {

    private static Session session;

    public static void setSession(Session session) {
        PersistedObjectsFactory.session = session;
    }


    public static ExternalLink createExternalLink(JpaEntityState jpaEntityState) {
        ExternalLink externalLink = EntityFactory.getDefaultExternalLink();

        saveAndFlush(externalLink, jpaEntityState);

        return externalLink;
    }

    public static List<ExternalLink> createExternalLinkList(int size) {
        List<ExternalLink> resultList = new ArrayList<>(size);

        for(int i=0; i < size; i++) {
            ExternalLink externalLink = EntityFactory.getDefaultExternalLink();
            session.save(externalLink);
            resultList.add(externalLink);
        }

        session.flush();
        session.clear();

        return resultList;
    }

    public static User createUser(JpaEntityState jpaEntityState) {
        User user = EntityFactory.getDefaultUser();

        saveAndFlush(user, jpaEntityState);

        return user;
    }

    public static Section createSection(JpaEntityState jpaEntityState) {
        Section section = EntityFactory.getDefaultSection();

        saveAndFlush(section, jpaEntityState);

        return section;
    }

    public static Branch createBranch(JpaEntityState jpaEntityState) {
        Section section = createSection(JpaEntityState.PERSISTENT);

        Branch branch = EntityFactory.getDefaultBranch();
        branch.setSection(section);
        section.addOrUpdateBranch(branch);

        saveAndFlush(branch, jpaEntityState);

        return branch;
    }


    public static Topic createTopic(JpaEntityState jpaEntityState) {
        Branch branch = createBranch(JpaEntityState.PERSISTENT);
        User user = createUser(JpaEntityState.PERSISTENT);

        Topic topic = EntityFactory.getDefaultTopic();
        topic.setBranch(branch);
        topic.setTopicStarter(user);

        saveAndFlush(topic, jpaEntityState);

        return topic;
    }


    public static List<Topic> createTopicList(int size) {
        List<Topic> result = new ArrayList<>();
        Branch branch = createBranch(JpaEntityState.PERSISTENT);
        User user     = createUser(JpaEntityState.PERSISTENT);

        for(int i=0; i < size; i++ ) {
            Topic topic = new Topic();
            topic.setTitle(random(Topic.MAX_TITLE_SIZE));
            topic.setCreationDate(new DateTime().plusDays(size - i));
            topic.setBranch(branch);
            topic.setTopicStarter(user);

            session.save(topic);

            result.add(topic);
        }

        session.flush();
        session.clear();

        return result;
    }


    public static Post createPost(JpaEntityState jpaEntityState) {
        Topic topic = createTopic(JpaEntityState.PERSISTENT);
        User  user  = createUser(JpaEntityState.PERSISTENT);

        Post post = EntityFactory.getDefaultPost();
        post.setTopic(topic);
        post.setUserCreated(user);
        topic.addPost(post);

        saveAndFlush(post, jpaEntityState);

        return post;
    }


    public static List<Post> createPostList(int size) {
        List<Post> resultList = new ArrayList<>();

        Topic topic = createTopic(JpaEntityState.PERSISTENT);
        User user   = createUser(JpaEntityState.PERSISTENT);
        
        for(int i=0; i < size; i++) {
            Post post = EntityFactory.getDefaultPost();
            post.setUserCreated(user);
            post.setTopic(topic);
            topic.addPost(post);

            session.save(post);
            resultList.add(post);
        }

        session.flush();
        session.clear();
        
        return resultList;
    }


    private static void saveAndFlush(BaseEntity entity, JpaEntityState jpaEntityState) {

        switch(jpaEntityState) {
            case TRANSIENT:
                             return;
            case PERSISTENT:
                             session.save(entity);
                             session.flush();       // in case pre-insert id generation strategy is used
            case DETACHED:
                             session.save(entity);
                             session.flush();
                             session.clear();
            default:
                             return;
        }
    }
}

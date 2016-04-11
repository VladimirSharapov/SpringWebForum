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
import org.shv.webforum.model.dao.PostDao;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.model.util.PersistedObjectsFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;


/**
 * @author Vladimir Sharapov
 */
public class PostDaoImplTest extends BaseDaoImplTest<Post> {

    private static int POST_LIST_SIZE = 3;
    private static int PAGE_SIZE = 5;

    @Autowired
    private PostDao postDao;

    @Override
    protected Crud getDao() {
        return postDao;
    }

    @Override
    protected void changeEntity(Post post) {
        post.setPostContent(random(Post.MAX_LENGTH));
    }

    @Test
    public void testGetPostsInTopic() throws Exception {

        List<Post> postList = PersistedObjectsFactory.createPostList(2 * PAGE_SIZE);
        Topic topic = postList.get(0).getTopic();

        PageRequest page1 = new PageRequest(0,PAGE_SIZE);
        Page<Post> fetchedPostList1 = postDao.getPostsInTopic(topic,page1);

        assertEquals(PAGE_SIZE,fetchedPostList1.getContent().size());

        for(int i=0; i < PAGE_SIZE; i++) {
            assertReflectionEquals(postList.get(i), fetchedPostList1.getContent().get(i));
        }

        PageRequest page2 = new PageRequest(1,PAGE_SIZE);
        Page<Post> fetchedPostList2 = postDao.getPostsInTopic(topic,page2);

        assertEquals(PAGE_SIZE,fetchedPostList2.getContent().size());

        for(int i=PAGE_SIZE; i < 2*PAGE_SIZE; i++) {
            assertReflectionEquals(postList.get(i), fetchedPostList2.getContent().get(i - PAGE_SIZE));
        }
    }

    @Test
    public void testGetPostCountInBranch() throws Exception {
        // create list of saved and detached posts
        List<Post> postList = PersistedObjectsFactory.createPostList(POST_LIST_SIZE);

        assertEquals(POST_LIST_SIZE, postDao.getPostCountInBranch(postList.get(0).getTopic().getBranch()));
    }

    @Test
    public void testGetPostsInBranch() throws Exception {
        // create list of saved and detached posts
        List<Post> postList = PersistedObjectsFactory.createPostList(POST_LIST_SIZE);

        List<Post> fetchedPostList = postDao.getPostsInBranch(postList.get(0).getTopic().getBranch());

        for(int i=0; i < POST_LIST_SIZE; i++ ) {
             assertReflectionEquals(postList.get(i),fetchedPostList.get(i));
        }
    }

    @Test
    public void testGetLastPosts() {
        List<Post> postList1 = PersistedObjectsFactory.createPostList(PAGE_SIZE);
        List<Post> postList2 = PersistedObjectsFactory.createPostList(PAGE_SIZE);
        List<Post> postList3 = PersistedObjectsFactory.createPostList(PAGE_SIZE);

        List<Topic> topicList = new ArrayList<>(3);
        topicList.add(postList1.get(0).getTopic());
        topicList.add(postList2.get(0).getTopic());
        topicList.add(postList3.get(0).getTopic());

        List<Long> lastPostIdList = new ArrayList<>(3);
        lastPostIdList.add(postList1.get(PAGE_SIZE-1).getId());
        lastPostIdList.add(postList2.get(PAGE_SIZE-1).getId());
        lastPostIdList.add(postList3.get(PAGE_SIZE-1).getId());

        List<Post> postList = postDao.getLastPosts(topicList);


      //  System.out.println("topic name: " + postList.get(0).getTopic().getTitle());
     //   assertEquals(3,postList.size());
      //  assertTrue(lastPostIdList.contains(postList.get(0).getId()));
      //  assertTrue(lastPostIdList.contains(postList.get(1).getId()));
       // assertTrue(lastPostIdList.contains(postList.get(2).getId()));
    }

    //create domain objects to test constraint violation
    @Override
    protected void fillParameters() {
        entity().setPostContent(random(Post.MAX_LENGTH + 1));
        entity().setPostContent(random(Post.MIN_LENGTH - 1));

        entity().setCreationDate(null);
    }
}

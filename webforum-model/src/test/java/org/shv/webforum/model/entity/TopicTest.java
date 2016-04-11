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
package org.shv.webforum.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Test for {@link org.shv.webforum.model.entity.Topic}
 *
 * @author Vladimir Sharapov
 */
public class TopicTest {

    @Test
    public void testAddPost() {
        Topic topic = createTopic();

        assertEquals(topic,topic.getPosts().get(0).getTopic());
        assertEquals(3,topic.getPosts().size());
    }

    @Test
    public void testGetLastPost() {
        Topic topic = createTopic();
        Post lastPost = new Post();

        topic.addPost(lastPost);

        assertEquals(lastPost,topic.getLastPost());
    }

    @Test
    public void testGetPostsCount() {
        Topic topic = createTopic();

        assertEquals(3,topic.getPostCount());

    }

    private Topic createTopic() {
        Topic topic = new Topic();

        for(int i=0; i < 3; i++) {
            topic.addPost(new Post());
        }

        return topic;
    }
}

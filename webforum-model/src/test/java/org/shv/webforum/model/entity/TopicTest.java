package org.shv.webforum.model.entity;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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

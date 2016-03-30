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

import org.shv.webforum.model.dao.BranchDao;
import org.shv.webforum.model.dao.PostDao;
import org.shv.webforum.model.dao.TopicDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.service.BranchService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Vladimir Sharapov
 */
public class BranchServiceImplTest {

    @Mock
    private BranchDao branchDao;

    @Mock
    private TopicDao topicDao;

    @Mock
    private PostDao postDao;


    private BranchService branchService;


    @Before
    public void  setUp() {
        initMocks(this);
        branchService = new BranchServiceImpl(branchDao,topicDao,postDao);
    }

    @Test
    public void testFillBranchStatistics() {
        int expectedTopicCount = 5;
        int expectedPostCount  = 10;
        Branch branch = new Branch("name","description");

        when(topicDao.getTopicCountInBranch(branch)).thenReturn(expectedTopicCount);
        when(postDao.getPostCountInBranch(branch)).thenReturn(expectedPostCount);

        branchService.fillBranchStatistics(branch);

        assertEquals(expectedTopicCount,branch.getTopicsCount());
        assertEquals(expectedPostCount,branch.getPostsCount());
    }

    @Test
    public void testUpdateLastPost() throws Exception {
        Branch branch = new Branch("name","description");
        Post lastPost = new Post();

        branchService.updateLastPost(branch,lastPost);

        assertSame(lastPost,branch.getLastPost());
    }

    @Test
    public void testGetAllPostsInBranch() throws Exception {
        Branch branch = new Branch("name","description");
        Post post = new Post();
        List<Post> postList = new ArrayList<>();
        postList.add(post);

        when(postDao.getPostsInBranch(branch)).thenReturn(postList);

        List<Post> actualPostList = branchService.getAllPostsInBranch(branch);

        assertEquals(postList.size(), actualPostList.size());
        assertSame(post,actualPostList.get(0));
    }
}

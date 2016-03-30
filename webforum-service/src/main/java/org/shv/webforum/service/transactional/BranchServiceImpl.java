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
import org.shv.webforum.service.common.BaseEntityServiceImpl;
import org.shv.webforum.service.common.NotFoundException;

import java.util.List;


/**
 * @author Vladimir Sharapov
 */
public class BranchServiceImpl extends BaseEntityServiceImpl<Branch, BranchDao> implements BranchService {

    private PostDao postDao;
    private TopicDao topicDao;

    protected BranchServiceImpl(BranchDao dao, TopicDao topicDao, PostDao postDao) {
        super(dao);
        this.topicDao = topicDao;
        this.postDao = postDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillBranchStatistics(Branch branch) {
          int postCount  = postDao.getPostCountInBranch(branch);
          int topicCount = topicDao.getTopicCountInBranch(branch);
          branch.setPostsCount(postCount);
          branch.setTopicsCount(topicCount);
    }

    /**
     * {@inheritDoc}
     */
    public Branch get(Long id) throws NotFoundException {
         return super.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLastPost(Branch branch, Post lastPost) {
        branch.setLastPost(lastPost);
        getDao().saveOrUpdate(branch);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getAllPostsInBranch(Branch branch) {
        return postDao.getPostsInBranch(branch);
    }
}

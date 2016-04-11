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
package org.shv.webforum.service;

import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.service.common.BaseEntityService;

import java.util.List;


/**
 * @author Vladimir Sharapov
 */
public interface BranchService extends BaseEntityService<Branch> {

    /**
     * Fill post count and topic count fields for input branch
     *
     * @param branch branch to be filled
     */
    public void fillBranchStatistics(Branch branch);

    /**
     * Updates last post for branch.
     *
     * @param branch     branch to which last post belongs
     * @param lastPost   post that was created last in the branch
     */
    public void updateLastPost(Branch branch, Post lastPost);

    /**
     * Finds all posts that belong to branch and returns them as a list.
     *
     * @param branch  branch in which to look for posts
     * @return all posts inside provided branch
     */
    public List<Post> getAllPostsInBranch(Branch branch);

}

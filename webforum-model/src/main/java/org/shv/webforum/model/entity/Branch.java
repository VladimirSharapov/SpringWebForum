package org.shv.webforum.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.shv.webforum.common.BaseEntity;

/**
 * Forum branch that contains topics and belongs to section {@link org.shv.webforum.model.entity.Section}
 *
 * @author Vladimir Sharapov
 */
public class Branch extends BaseEntity {

    private static final String BRANCH_DESCRIPTION_ILLEGAL_LENGTH = "{branch.description.length_constraint_violation}";
    private static final String BRANCH_CANT_BE_VOID = "{branch.name.emptiness_constraint_violation}";
    private static final String BRANCH_NAME_ILLEGAL_LENGTH = "{branch.name.length_constraint_violation}";

    public  static final int BRANCH_NAME_MAX_LENGTH = 80;
    public  static final int BRANCH_DESCRIPTION_MAX_LENGTH = 255;

    @NotBlank(message = BRANCH_CANT_BE_VOID)
    @Length(max = BRANCH_NAME_MAX_LENGTH, message = BRANCH_NAME_ILLEGAL_LENGTH)
    private String name;

    @Length(max = BRANCH_DESCRIPTION_MAX_LENGTH, message = BRANCH_DESCRIPTION_ILLEGAL_LENGTH)
    private String description;

    private Section section;

    private Post lastPost;

    private Integer topicsCount;

    private Integer postsCount;


    /**
     * Default constructor, protected for using only by hibernate
     */
    protected Branch() {
    }

    /**
     * Create branch with name and description
     *
     * @param name - name for new branch
     * @param description - description for new Branch
     */
    public Branch(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Get branch name which briefly describes the topics contained in it.
     *
     * @return  branch name
     */
    public String getName() {
        return name;
    }

    /**
     * Set branch name.
     *
     * @param name - branch name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get branch description.
     *
     * @return branch description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set branch description which contains additional information about the
     * branch.
     *
     * @param description - branch description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return its parent section
     */
    public Section getSection() {
        return section;
    }

    /**
     * Sets the section to which this branch belongs.
     *
     * @param section the parent section
     */
    public void setSection(Section section) {
        this.section = section;
    }

    /**
     * @return last post {@link org.shv.webforum.model.entity.Post} in this branch
     */
    public Post getLastPost() {
        return lastPost;
    }

    /**
     * Set last post in this branch
     *
     * @param lastPost last post in this branch
     */
    public void setLastPost(Post lastPost) {
        this.lastPost = lastPost;
    }

    /**
     * @return number of topic in this branch
     */
    public Integer getTopicsCount() {
        return topicsCount;
    }

    /**
     * Set number of topics in this branch
     *
     * @param topicsCount number of topics
     */
    public void setTopicsCount(Integer topicsCount) {
        this.topicsCount = topicsCount;
    }

    /**
     * Get number of posts in this branch. This is sum of number of posts inside each topic
     * that belongs to this branch.
     *
     * @return number of posts in this branch
     */
    public Integer getPostsCount() {
        return postsCount;
    }

    /**
     * Set number of posts in this branch
     *
     * @param postsCount number of posts in this branch
     */
    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    @Override
    public String toString() {
        return "Branch [id=" + getId() + ", name=" + name + ", description=" + description + "]";
    }}

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.shv.webforum.common.BaseEntity;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.Size;


/**
 * Forum branch that contains topics and belongs to section {@link org.shv.webforum.model.entity.Section}
 *
 * @author Vladimir Sharapov
 */
@Entity
public class Branch extends BaseEntity {

    private static final String BRANCH_DESCRIPTION_ILLEGAL_LENGTH = "{branch.description.length_constraint_violation}";
    private static final String BRANCH_CANT_BE_VOID = "{branch.name.emptiness_constraint_violation}";
    private static final String BRANCH_NAME_ILLEGAL_LENGTH = "{branch.name.length_constraint_violation}";

    public static final int BRANCH_NAME_MAX_LENGTH = 80;
    public static final int BRANCH_DESCRIPTION_MAX_LENGTH = 255;

    @NotBlank(message = BRANCH_CANT_BE_VOID)
    @Size(max = BRANCH_NAME_MAX_LENGTH, message = BRANCH_NAME_ILLEGAL_LENGTH)
    private String name;

    @Size(max = BRANCH_DESCRIPTION_MAX_LENGTH, message = BRANCH_DESCRIPTION_ILLEGAL_LENGTH)
    private String description;

    @ManyToOne
    @JoinColumn(name = "SECTION")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "LASTPOST")
    private Post lastPost;

    @Transient
    private int topicsCount;

    @Transient
    private int postsCount;

    /**
     * Default constructor, protected for using only by hibernate
     */
    protected Branch() {
    }

    /**
     * Create branch with name and description
     *
     * @param name        - name for new branch
     * @param description - description for new Branch
     */
    public Branch(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Get branch name which briefly describes the topics contained in it.
     *
     * @return branch name
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
    public int getTopicsCount() {
        return topicsCount;
    }

    /**
     * Set number of topics in this branch
     *
     * @param topicsCount number of topics
     */
    public void setTopicsCount(int topicsCount) {
        this.topicsCount = topicsCount;
    }

    /**
     * Get number of posts in this branch. This is sum of number of posts inside each topic
     * that belongs to this branch.
     *
     * @return number of posts in this branch
     */
    public int getPostsCount() {
        return postsCount;
    }

    /**
     * Set number of posts in this branch
     *
     * @param postsCount number of posts in this branch
     */
    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    @Override
    public String toString() {
        return "Branch [id=" + getId() + ", name=" + name + ", description=" + description + "]";
    }

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Branch rhs = (Branch) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .append(description, rhs.description)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(description)
                .toHashCode();
    }
}

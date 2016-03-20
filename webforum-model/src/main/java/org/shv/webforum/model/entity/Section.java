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

import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;


/**
 * Forum section that contains branches
 *
 * @author Vladimir Sharapov
 */
@NamedQueries(
        @NamedQuery(
                // we want to fetch all the data needed to display start page of forum at once,
                // that means using just one trip to database
                name="allSections", query="select s from Section s " +
                                          "join fetch s.branches b " +
                                          "left join fetch b.lastPost lp " +
                                          "left join fetch lp.userCreated order by s.position"
        )
)
@Entity
public class Section extends BaseEntity {

    private static final String SECTION_DESCRIPTION_ILLEGAL_LENGTH = "{section.description.length_constraint_violation}";
    private static final String SECTION_NAME_ILLEGAL_LENGTH = "{section.name.length_constraint_violation}";
    private static final String SECTION_CANT_BE_VOID = "{section.name.emptiness_constraint_violation}";

    public static final int SECTION_NAME_MAX_LENGTH = 80;
    public static final int SECTION_DESCRIPTION_MAX_LENGTH = 255;

    @NotBlank(message = SECTION_CANT_BE_VOID)
    @Size(max = SECTION_NAME_MAX_LENGTH, message = SECTION_NAME_ILLEGAL_LENGTH)
    private String name;

    @Size(max = SECTION_DESCRIPTION_MAX_LENGTH, message = SECTION_DESCRIPTION_ILLEGAL_LENGTH)
    private String description;

    private Integer position;

    @OrderBy("ID ASC")
    @OneToMany(mappedBy = "section")
    private List<Branch> branches = new ArrayList<>();

    /**
     * Default constructor, is used only by Hibernate and subclasses
     */
    protected Section() {
    }

    /**
     * Creates a section with empty list of branches setting section a name
     *
     * @param name for new section
     */
    public Section(String name) {
        this.name = name;
    }

    /**
     * Constructor with name and description, creates a section with empty list of branches
     *
     * @param name - name for new section
     * @param description - description for new section
     */
    public Section(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Set section name which briefly describes the topics contained in it.
     *
     * @return name section.
     */
    public String getName() {
        return name;
    }

    /**
     * Set section name.
     *
     * @param name - name for section.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get section description.
     *
     * @return description for section
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set section description which contains additional information about the
     * section.
     *
     * @param description - description for section
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get section position.
     *
     * @return position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Set section position.
     *
     * @param position - position for section
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * Get section branches
     *
     * @return list of branches
     */
    public List<Branch> getBranches() {
        return branches;
    }

    /**
     * Set section branches
     *
     * @param branches - list of branches
     */
    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    /**
     * Adds branch to the section or updates if it exist.
     *
     * @param branch the branch for adding to section
     */
    public void addOrUpdateBranch(Branch branch) {
        for (int index = 0; index < branches.size(); index++) {
            Long id = branches.get(index).getId();

            if (id != null && id == branch.getId()) {
                branches.set(index, branch);
                return;
            }
        }

        branches.add(branch);
    }

    /**
     * Delete branch from the section.
     *
     * @param branch the branch for deleting from section
     */
    public void deleteBranch(Branch branch) {
        branches.remove(branch);
    }

    @Override
    public String toString() {
        return "Section [id=" + getId() + ", name=" + name + ", description=" + description + "]";
    }

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Section rhs = (Section) obj;
        return new EqualsBuilder()
                .append(position, rhs.position)
                .append(name,rhs.name)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(position)
                .append(name)
                .toHashCode();
    }
}

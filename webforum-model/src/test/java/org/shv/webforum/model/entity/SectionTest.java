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

import org.shv.webforum.model.util.EntityFactory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * Test for {@link org.shv.webforum.model.entity.Section}
 *
 * @author Vladimir Sharapov
 */
public class SectionTest {

    public static final int BRANCH_SIZE = 3;
    public static final Long BRANCH_ID = 10L;

    @Test
    public void testAddBranch() {
        Section section = EntityFactory.getDefaultSection();
        Branch branch = EntityFactory.getDefaultBranch();

        section.addOrUpdateBranch(branch);

        assertEquals(1, section.getBranches().size());
    }

    @Test
    public void testUpdateBranch() {
        Section section  = EntityFactory.getDefaultSectionWithBranches(BRANCH_SIZE);
        Branch branchToUpdate = section.getBranches().get(0);
        branchToUpdate.setId(BRANCH_ID);

        Branch newBranch = EntityFactory.getDefaultBranch();
        newBranch.setId(BRANCH_ID);

        section.addOrUpdateBranch(newBranch);

        Branch updatedBranch = section.getBranches().get(0);

        assertFalse(branchToUpdate.getName() == updatedBranch.getName());
        assertEquals(newBranch.getName(), updatedBranch.getName());
        assertEquals(BRANCH_SIZE,section.getBranches().size());
    }

    @Test
    public void testDeleteBranch() {
        Section section = EntityFactory.getDefaultSectionWithBranches(BRANCH_SIZE);
        Branch branchToDelete = section.getBranches().get(0);

        section.deleteBranch(branchToDelete);

        assertEquals(BRANCH_SIZE - 1,section.getBranches().size());
    }
}

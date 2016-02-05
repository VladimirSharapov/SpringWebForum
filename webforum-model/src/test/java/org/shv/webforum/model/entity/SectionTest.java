package org.shv.webforum.model.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import org.shv.webforum.model.util.EntityFactory;

import static junit.framework.Assert.assertEquals;


/**
 * Test for {@link org.shv.webforum.model.entity.Section} class
 *
 * @author Vladimir Sharapov
 */
public class SectionTest {

    public static final Long BRANCH_ID = 2L;
    public static final String NEW_BRANCH_NAME = "newBranchName";

    private Section section;
    private Branch branch1;
    private Branch branch2;

    @Before
    public void setUp() {
        section = EntityFactory.getDefaultSection();

        List<Branch> branchList = new ArrayList<>();
        branch1 = new Branch("branch1","");
        branch1.setId(1L);

        branch2 = new Branch("branch2","");
        branch2.setId(BRANCH_ID);

        branchList.add(branch1);
        branchList.add(branch2);

        section.setBranches(branchList);
    }

    @Test
    public void testAddBranch() {
        Section section = EntityFactory.getDefaultSection();

        section.addOrUpdateBranch(new Branch("",""));

        assertEquals(1,section.getBranches().size());
    }

    @Test
    public void testUpdateBranch() {
        Branch newBranch = new Branch(NEW_BRANCH_NAME,"");
        newBranch.setId(BRANCH_ID);

        section.addOrUpdateBranch(newBranch);

        assertEquals(NEW_BRANCH_NAME,section.getBranches().get(1).getName());
        assertEquals(2,section.getBranches().size());
    }

    @Test
    public void testDeleteBranch() {
        Branch branchToDelete = new Branch("","");
        branchToDelete.setId(BRANCH_ID);

        section.deleteBranch(branchToDelete);

        assertEquals(1,section.getBranches().size());
    }


}

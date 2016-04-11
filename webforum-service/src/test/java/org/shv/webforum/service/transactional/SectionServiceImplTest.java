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

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.shv.webforum.model.dao.SectionDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Section;
import org.shv.webforum.service.BranchService;
import org.shv.webforum.service.SectionService;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.*;

/**
 * @author Vladimir Sharapov
 */
public class SectionServiceImplTest {

    @Mock
    private SectionDao sectionDao;

    @Mock
    private BranchService branchService;

    private SectionService sectionService;

    @Before
    public void setUp() {
        initMocks(this);
        sectionService = new SectionServiceImpl(sectionDao,branchService);
    }

    @Test
    public void testPrepareSectionsForView() {
        List<Section> sections = Arrays.asList(new Section("", ""),new Section("",""));
        List<Branch> branches1 = Arrays.asList(new Branch("",""),new Branch("",""));
        List<Branch> branches2 = Arrays.asList(new Branch("",""),new Branch("",""));

        sections.get(0).setBranches(branches1);
        sections.get(1).setBranches(branches2);

        sectionService.prepareSectionsForView(sections);
        verify(branchService, Mockito.times(branches1.size() + branches2.size())).fillBranchStatistics(Mockito.any(Branch.class));
    }
}

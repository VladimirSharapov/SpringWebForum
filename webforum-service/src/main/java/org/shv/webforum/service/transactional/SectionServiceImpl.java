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

import org.shv.webforum.model.dao.SectionDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Section;
import org.shv.webforum.service.BranchService;
import org.shv.webforum.service.SectionService;
import org.shv.webforum.service.common.BaseEntityServiceImpl;
import org.shv.webforum.service.common.NotFoundException;

import java.util.List;


/**
 * @author Vladimir Sharapov
 */
public class SectionServiceImpl extends BaseEntityServiceImpl<Section, SectionDao> implements SectionService {

    private BranchService branchService;

    protected SectionServiceImpl(SectionDao dao, BranchService branchService) {
        super(dao);
        this.branchService = branchService;
    }

    /**
     * {@inheritDoc}
     */
    public Section get(Long sectionId) throws NotFoundException {
        return super.get(sectionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Section> getAll() {
        return getDao().getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareSectionsForView(List<Section> sectionList) {
        for(Section section : sectionList) {
            for(Branch branch : section.getBranches()) {
                branchService.fillBranchStatistics(branch);
            }
        }
    }
}

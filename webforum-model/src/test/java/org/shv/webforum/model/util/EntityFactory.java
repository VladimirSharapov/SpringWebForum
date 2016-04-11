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
package org.shv.webforum.model.util;

import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.shv.webforum.model.entity.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.RandomStringUtils.random;


/**
 * Factory class to produce entities that we need for tests.
 *
 * @author Vladimir Sharapov
 */
public final class EntityFactory {

    public static final String HTTP_SCHEME = "http://";

    private EntityFactory() {
    }

    public static User getDefaultUser() {
        User user = new User();
        user.setUsername(RandomStringUtils.random(User.USERNAME_MAX_LENGTH));
        user.setPassword(RandomStringUtils.random(User.PASSWORD_MAX_LENGTH));
        user.setEmail(RandomStringUtils.random(User.EMAIL_MAX_LENGTH-5,true,true) + "@" + RandomStringUtils.random(1,true,true) + ".ru");
     //   user.setEmail("vald@mail.ru");
        user.setRegistrationDate(new DateTime());
        return user;
    }

    public static Section getDefaultSection() {
        Section section = new Section(random(Section.SECTION_NAME_MAX_LENGTH),random(Section.SECTION_DESCRIPTION_MAX_LENGTH));
        section.setPosition(1);
        return section;
    }

    public static Section getDefaultSectionWithBranches(int size) {
        Branch branch = null;
        List<Branch> branchList = new ArrayList<>();

        Section section = EntityFactory.getDefaultSection();

        for(int i=0; i < size; i++) {
            branch = getDefaultBranch();
            branch.setSection(section);
            branchList.add(branch);
        }

        section.setBranches(branchList);

        return section;
    }

    public static Branch getDefaultBranch() {
        Branch branch = new Branch(random(Branch.BRANCH_NAME_MAX_LENGTH),random(Branch.BRANCH_DESCRIPTION_MAX_LENGTH));
        return branch;
    }

    public static Topic getDefaultTopic() {
        Topic topic = new Topic();
        topic.setTitle(random(Topic.MAX_TITLE_SIZE));
        topic.setCreationDate(new DateTime());
        return topic;
    }

    public static Post getDefaultPost() {
        Post post = new Post();
        post.setPostContent(random(Post.MAX_LENGTH));
        post.setCreationDate(new DateTime());
        post.setModificationDate(new DateTime());
        return post;
    }

    public static ExternalLink getDefaultExternalLink() {
        ExternalLink result = new ExternalLink();
        result.setUrl(HTTP_SCHEME + random(ExternalLink.URL_MAX_SIZE - HTTP_SCHEME.length(),true,true));
        result.setTitle(random(ExternalLink.TITLE_MAX_SIZE));
        result.setHint(random(ExternalLink.HINT_MAX_SIZE));
        return result;
    }

    public static List<ExternalLink> getExternalLinks(int size) {
        List<ExternalLink> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(getDefaultExternalLink());
        }
        return result;
    }
}

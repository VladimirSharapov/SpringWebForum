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
package org.shv.webforum.model.validation;

/**
 * This interface is created to represent validation group,
 * i.e. it is used as a value to group property of various restriction annotations
 * (like Size, NotNull etc). Consider the following situation: from the web form we
 * submit the object, but not all required fields are filled (because some fields are supposed to be filled
 * on the server, for example, creationDate). So, we need to validate only certain fields of the object, in
 * particular those who have restrictions with group=FormData.class. This can be used instead of
 * creating DTO objects
 *
 * @author Vladimir Sharapov
 */
public interface FormData  {
}

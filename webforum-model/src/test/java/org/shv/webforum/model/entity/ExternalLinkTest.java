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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Test for {@link org.shv.webforum.model.entity.ExternalLink}
 *
 * @author Vladimir Sharapov
 */
public class ExternalLinkTest {

    @Test
    public void testThreeArgsConstructorAndGetters() throws Exception {
        String url = "http://vladforum.org";
        String title = "title";
        String hint = "hint";
        ExternalLink externalLink = new ExternalLink(url, title, hint);

        assertEquals(url, externalLink.getUrl());
        assertEquals(title, externalLink.getTitle());
        assertEquals(hint, externalLink.getHint());
    }

    @Test
    public void testLinkWithNoProtocol() {
        String url = "vladforum.org";
        String title = "title";
        String hint = "hint";
        ExternalLink externalLink = new ExternalLink(url, title, hint);

        assertEquals("http://" + url, externalLink.getUrl());
        assertEquals(title, externalLink.getTitle());
        assertEquals(hint, externalLink.getHint());
    }

    @Test
    public void testEmptyLink() {
        String url = "";
        String title = "title";
        String hint = "hint";
        ExternalLink externalLink = new ExternalLink(url, title, hint);

        assertEquals("", externalLink.getUrl());
        assertEquals(title, externalLink.getTitle());
        assertEquals(hint, externalLink.getHint());
    }

    @Test
    public void testNullHint() {
        String url = "vladforum.org";
        String title = "title";
        String hint = null;
        ExternalLink externalLink = new ExternalLink(url,title,hint);

        assertEquals("http://" + url, externalLink.getUrl());
        assertEquals(title, externalLink.getTitle());
        assertNull(externalLink.getHint());
    }

    @Test
    public void testHintTrimming() {
        String url = "vladforum.org";
        String title = "title";
        String hint =  "  hint  ";
        ExternalLink externalLink = new ExternalLink(url,title,hint);

        assertEquals("http://" + url, externalLink.getUrl());
        assertEquals(title, externalLink.getTitle());
        assertEquals(hint.trim(), externalLink.getHint());
    }
}

package org.shv.webforum.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test for {@link org.shv.webforum.model.entity.ExternalLink} class
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

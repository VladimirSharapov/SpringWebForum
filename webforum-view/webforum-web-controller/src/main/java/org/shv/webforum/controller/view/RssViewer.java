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
package org.shv.webforum.controller.view;

import org.shv.webforum.model.entity.Topic;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;

import org.apache.commons.lang.StringUtils;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Class forms a RSS feed view.
 *
 * @author Vladimir Sharapov
 */
public class RssViewer extends AbstractRssFeedView {

    private static final String DEFAULT_FEED_TITLE = "";
    private static final String DEFAULT_FEED_DESCRIPTION = "";

    /**
     * Set meta data for all RSS feed
     *
     * @param newsComponents components of the RSS feed
     * @param feed    news feed
     * @param request http request
     */
    @Override
    protected void buildFeedMetadata(Map<String, Object> newsComponents, Channel feed,
                                     HttpServletRequest request) {

        String feedTitle = DEFAULT_FEED_TITLE;
        String feedDescription = DEFAULT_FEED_DESCRIPTION;

        feed.setTitle(feedTitle);
        feed.setDescription(feedDescription);
        feed.setLink(buildURL(request));

        super.buildFeedMetadata(newsComponents, feed, request);
    }

    /**
     * Set list data item news in RSS feed
     *
     * @param newsComponents components of the RSS feed
     * @param request  http request
     * @param response http response
     * @return list items
     * @throws java.io.IOException i/o exception
     */
    @Override
    protected List<Item> buildFeedItems(Map<String, Object> newsComponents,
                                        HttpServletRequest request, HttpServletResponse response)
        throws IOException {

        String url = buildURL(request);
        List<Topic> listContent = (List<Topic>) newsComponents.get("topics");
        if (listContent == null) {
            response.sendRedirect(request.getContextPath() + "/errors/404");
            return null;
        }
        List<Item> items = new ArrayList<>(listContent.size());

        for (Topic topic : listContent) {
            items.add(createFeedItem(topic, url));
        }

        response.setContentType(getContentType());
        return items;
    }

    /**
     * Create news item
     *
     * @param topic   news topic
     * @param url building URL
     * @return item for news feed
     */
    private Item createFeedItem(Topic topic, String url) {

        Item item = new Item();
        Description description = new Description();
        description.setType("text");
        String postContent = topic.getLastPost().getPostContent();
        postContent = skipInValidXMLChars(postContent);
        description.setValue(postContent);

        Content content = new Content();
        item.setContent(content);

        item.setTitle(topic.getTitle());
        item.setAuthor(topic.getLastPost().getUserCreated().getUsername());

        item.setLink(url + "/posts/" + topic.getLastPost().getId() + "#" + topic.getLastPost().getId());

        item.setDescription(description);
        item.setPubDate(topic.getLastPost().getCreationDate().toDate());
        return item;
    }

    /**
     * The implementation of building url
     *
     * @param request HttpServletRequest
     * @return url
     */
    private String buildURL(HttpServletRequest request) {
        return request.getScheme()
                + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getContextPath();
    }

    /**
     * Method for skip invalid characters in content for RSS feed.
     * <p/>
     * Description valid chars in XML specification
     * http://www.w3.org/TR/REC-xml/#charsets
     *
     * @param in - post content.
     */
    private static String skipInValidXMLChars(String in) {
        if (StringUtils.isBlank(in)) return StringUtils.EMPTY;
        String pattern = "[^"
                + "\u0009\r\n"
                + "\u0020-\uD7FF"
                + "\uE000-\uFFFD"
                + "\ud800\udc00-\udbff\udfff"
                + "]";
        return in.replaceAll(pattern, StringUtils.EMPTY);
    }

}
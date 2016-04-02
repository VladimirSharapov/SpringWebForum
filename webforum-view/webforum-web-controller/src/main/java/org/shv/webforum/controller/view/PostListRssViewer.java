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

import org.shv.webforum.model.entity.Post;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 * Class forms a RSS feed view for posts list.
 *
 * @author Vladimir Sharapov
 */
public class PostListRssViewer extends AbstractRssFeedView {

    public static final String DEFAULT_FEED_TITLE = "";
    public static final String DEFAULT_FEED_DESCRIPTION = "";

    /**
     * Sets meta data for the whole RSS feed
     *
     * @param model   RSS model
     * @param feed    RSS feed
     * @param request http request
     */
    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
                                     HttpServletRequest request) {
        String title = (String)model.get("feedTitle");
        if (title == null) {
            title = DEFAULT_FEED_TITLE;
        }

        String description = (String)model.get("feedDescription");
        if (description == null) {
            description = DEFAULT_FEED_DESCRIPTION;
        }

        String link = buildURL(request);
        String suffix = (String) model.get("urlSuffix");
        if(suffix != null) {
            link += suffix;
        }

        feed.setTitle(title);
        feed.setDescription(description);
        feed.setLink(link);

        super.buildFeedMetadata(model, feed, request);
    }

    /**
     * Set list data item news in RSS feed
     *
     * @param model    model containing information about RSS items
     * @param request  http request
     * @param response http response
     * @return list of RSS items
     * @throws java.io.IOException i/o exception
     */
    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String url = buildURL(request);
        List<Post> posts = (List<Post>) model.get("posts");
        if (posts == null) {
            response.sendRedirect(request.getContextPath() + "/errors/404");
            return null;
        }
        List<Item> items = new ArrayList<Item>(posts.size());

        for (Post post : posts) {
            items.add(createFeedItem(post, url));
        }

        response.setContentType(getContentType());
        return items;
    }

    /**
     * Creates feed item with information about the post
     *
     * @param post post to add to the feed
     * @param componentUrl base url of the forum component
     * @return item for the RSS feed
     */
    private Item createFeedItem(Post post, String componentUrl) {

        Item item = new Item();

        Content content = new Content();
        content.setType(Content.TEXT);
        content.setValue(post.getPostContent());
        item.setContent(content);

        item.setTitle(post.getTopic().getTitle());
        item.setAuthor(post.getUserCreated().getUsername());

        item.setLink(componentUrl + "/posts/" + post.getId());

        item.setPubDate(post.getCreationDate().toDate());
        return item;
    }

    /**
     * Builds base url for the forum items (branches, posts,...)
     *
     * @param request HttpServletRequest
     * @return base url for the forum items
     */
    private String buildURL(HttpServletRequest request) {
        return request.getScheme()
                + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getContextPath();
    }

}
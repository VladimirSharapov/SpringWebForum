package org.shv.webforum.model.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;

import org.shv.webforum.common.BaseEntity;

/**
 * Stores information about external link. Such links are shown on every page of forum at the top of it.
 *
 * @author Vladimir Sharapov
 */
@Entity
public class ExternalLink extends BaseEntity {

    public static final int TITLE_MAX_SIZE = 30;
    public static final int TITLE_MIN_SIZE = 1;
    public static final int URL_MAX_SIZE = 255;
    public static final int HINT_MAX_SIZE = 128;
    public static final String HTTP_PROTOCOL_PREFIX = "http://";
    public static final String PROTOCOL_SEPARATOR = "://";


    @NotNull(message = "{validation.not_null}")
    @Size(max = URL_MAX_SIZE, message = "{validation.links.url.length}")
    @URL
    private String url;

    @NotNull(message = "{validation.not_null}")
    @Size(max = TITLE_MAX_SIZE, message = "{validation.links.title.length}")
    @NotBlank(message = "{validation.links.title.not_blank}")
    private String title;

    @NotNull(message = "{validation.not_null}")
    @Size(max = HINT_MAX_SIZE, message = "{validation.links.hint.length}")
    private String hint;

    /**
     * Only for hibernate usage.
     */
    public ExternalLink() {
    }

    /**
     * @param url   external URL, that forum will point to ( for example http://github.com/VladimirSharapov )
     * @param title URL title, t hat will be shown to user ( for example 'My Webforum project' )
     * @param hint  URL hint or description, for example 'project on github'
     */
    public ExternalLink(String url, String title, String hint) {
        setUrl(url);
        this.title = title;
        setHint(hint);
    }

    /**
     * @return  external URL, that forum will point to ( for example http://github.com/VladimirSharapov )
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url external URL, that forum will point to ( for example http://github.com/VladimirSharapov )
     */
    public void setUrl(String url) {
        if (url != null && !url.equals("")) {
            url = url.trim();
            if (!url.contains(PROTOCOL_SEPARATOR)) {
                url = HTTP_PROTOCOL_PREFIX + url;
            }
        }

        this.url = url;
    }

    /**
     * @return title, that will be shown to user ( for example 'My Webforum project' )
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title, that will be shown to user ( for example 'My Webforum project' )
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return URL hint or description, for example 'project on github'
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint URL hint or description, for example 'project on github'
     */
    public void setHint(String hint) {
        if (hint != null) {
             hint = hint.trim();
        }

        this.hint = hint;
    }


}

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.shv.webforum.common.BaseEntity;

import org.hibernate.validator.constraints.URL;
import org.shv.webforum.model.validation.annotation.NotBlankSized;

import javax.persistence.Entity;


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
    public static final int HINT_MIN_SIZE = 1;
    public static final int HINT_MAX_SIZE = 128;
    public static final String HTTP_SCHEME = "http://";
    public static final String PROTOCOL_SEPARATOR = "://";

    @URL
    @NotBlankSized(max = URL_MAX_SIZE, message = "{links.url.length}")
    private String url;

  //  @NotBlankSized(min=TITLE_MIN_SIZE, max=TITLE_MAX_SIZE, message = "{links.title.not_blank}")
    private String title;

    @NotBlankSized(min=HINT_MIN_SIZE, max = HINT_MAX_SIZE, message = "{links.hint.not_blank}")
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
                url = HTTP_SCHEME + url;
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

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }

        ExternalLink rhs = (ExternalLink) obj;
        return new EqualsBuilder()
                .append(url,rhs.url)
                .append(hint, rhs.hint)
                .append(title, rhs.title)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(url)
                .append(hint)
                .append(title)
                .toHashCode();
    }
}

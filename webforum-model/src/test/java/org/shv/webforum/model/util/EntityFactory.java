package org.shv.webforum.model.util;

import org.shv.webforum.model.entity.ExternalLink;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class to produce entities that we need for tests.
 *
 * @author Vladimir Sharapov
 */
public final class EntityFactory {

    public static final String EXTERNAL_LINK_URL = "http://github.com/VladimirSharapov";
    public static final String EXTERNAL_LINK_TITLE = "Web Forum";
    public static final String EXTERNAL_LINK_HINT = "Just a demo project to show my skills";

    private EntityFactory() {
    }

    public static ExternalLink getDefaultExternalLink() {
        ExternalLink result = new ExternalLink();
        result.setUrl(EXTERNAL_LINK_URL);
        result.setTitle(EXTERNAL_LINK_TITLE);
        result.setHint(EXTERNAL_LINK_HINT);
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

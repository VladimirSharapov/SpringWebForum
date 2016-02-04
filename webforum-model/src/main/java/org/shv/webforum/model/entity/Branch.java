package org.shv.webforum.model.entity;

import org.shv.webforum.common.BaseEntity;

/**
 * Forum branch that contains topics
 *
 * @author Vladimir Sharapov
 */
public class Branch extends BaseEntity {


    private String name;

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

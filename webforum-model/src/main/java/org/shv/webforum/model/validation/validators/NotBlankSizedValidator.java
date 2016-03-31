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
package org.shv.webforum.model.validation.validators;

import org.shv.webforum.model.validation.annotation.NotBlankSized;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link NotBlankSized} annotation
 *
 * @author Vladimir Sharapov
 */
public class NotBlankSizedValidator implements ConstraintValidator<NotBlankSized, String> {

    private int min;
    private int max;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(NotBlankSized constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    /**
     * Validates input string size
     *
     * @param value string with {@link NotBlankSized} annotation
     * @param context validation context
     *
     * @return true if string not null and has size between the specified boundaries (included).
     *         false otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null
                && value.length() >= min
                && value.length() <= max;
    }
}

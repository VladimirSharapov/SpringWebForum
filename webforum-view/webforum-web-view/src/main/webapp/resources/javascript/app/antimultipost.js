/*
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
/** Namespace for this file */
var Antimultipost = {};

/**
 * Checks if given element being already submitted
 * @param element element to check
 */
Antimultipost.beingSubmitted = function(element) {
	if (element.attr('submitted')) {
		return true;
	};
	return false;
}

/**
 * Mark element as being submitted
 * @param element element to mark
 */
Antimultipost.disableSubmit = function(element) {
	element.attr('submitted', 'true');
}

/**
 * Mark element as not being submitted
 * @param element element to mark
 */
Antimultipost.enableSubmit = function(element) {
	element.removeAttr('submitted');
}
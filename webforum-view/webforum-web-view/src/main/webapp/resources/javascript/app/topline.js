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

jQuery(document).ready(function(){

	// Tooltips
    jQuery('#logo-container').tooltip({delay: 250, placement: 'bottom'});

    jQuery('.rss-ref').tooltip({delay: 250, placement: 'bottom'});

    jQuery('#lang-selector-toggle').tooltip({delay: 250, placement: 'bottom'});

    jQuery('.user-profile-view').tooltip({delay: 250, placement: 'bottom'});

    jQuery('.last-post-view').tooltip({delay: 250, placement: 'top'});


    // remove selected local from language selector
    jQuery('#lang-' + $localeCode).remove();

 /*   jQuery('#lang-en').click(function(){
      //  $.post("/spring-webforum/users/save/2");
        jQuery('#formsPlaceholder').append('<form action="/spring-webforum/users/save/2" method="post" id="newForm">' +
        '<input type="text" name="lang" value="en"/></form>');
        jQuery('#newForm').submit();
    }); */


});
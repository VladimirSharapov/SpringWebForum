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
var Keymaps = {};

var escCode = 27;
var enterCode = 13;
var charMin = 60;
var charMax = 90;
var tabCode = 9;
var upCode = 38;
var downCode = 40;

Keymaps.review = function (e) {
    if (e.ctrlKey && e.keyCode == enterCode) {
        e.preventDefault();
        $(".review-container-controls-ok").click();
    }
    else if (e.keyCode == escCode) {
        e.preventDefault();
        $(".review-container-controls-cancel").click();
    }
};

Keymaps.reviewConfirmRemoveButton = function (e) {
    if ((e.keyCode || e.charCode) == tabCode) {
        e.preventDefault();
        $(e.target).parents('.modal').find('.cancel').focus();
    }
}

Keymaps.reviewCancelRemoveButton = function (e) {
    if ((e.keyCode || e.charCode) == tabCode) {
        e.preventDefault();
        $(e.target).parents('.modal').find('.btn-primary').focus();
    }
}

Keymaps.registrationSubmit = function (e) {
    if ((e.keyCode || e.charCode) == tabCode) {
        e.preventDefault();
        $('#signup-modal-dialog').find("#username").focus();
    }
}

Keymaps.registrationPassConfirm = function (e) {
    if ((e.keyCode || e.charCode) == tabCode) {
        e.preventDefault();
        $('#signup-modal-dialog').find(".captcha").focus();
    }
}

Keymaps.signinSubmit = function (e) {
    if ((e.keyCode || e.charCode) == tabCode) {
        e.preventDefault();
        $('#signin-modal-dialog').find("#userName").focus();
    }
}

Keymaps.linksEditor = function (e) {
    if ((e.keyCode || e.charCode) == enterCode) {
        var but = $('#mainLinksEditor  #saveLink:visible')[0];
        var targetId = $(e.target).attr('id');

        var enterNavigation = ['linkTitle', 'linkUrl', 'linkHint', 'saveLink'];

        if ($('#mainLinksEditor #linkTitle:visible')[0]) {
            for (var i = 0; i < enterNavigation.length - 1; ++i) {
                if (targetId == enterNavigation[i]) {
                    var nextElementSelector = '#mainLinksEditor  #' + enterNavigation[i + 1];
                    e.preventDefault();
                    if($.browser.mozilla){
                        setTimeout(function(){
                            $(nextElementSelector).focus();
                        }, 0);
                    }
                    else {
                        $(nextElementSelector).focus();
                    }
                    return;
                }
            }
        }

        if (but && $(e.target).attr('id') != 'cancelLink') {
            e.preventDefault();
            if($.browser.mozilla){
                setTimeout(function(){
                    but.click();
                }, 0);
            }
            else {
                but.click();
            }
        }
    }

    if ((e.keyCode || e.charCode) == escCode) {
        var but = $('#mainLinksEditor #cancelLink:visible')[0]
        if (but) {
            e.preventDefault();
            but.click();
            $('#mainLinksEditor').focus();
        } else {
            $('#mainLinksEditor .close').click();
        }
    }
}

Keymaps.uploadBannerCancelButton = function (e) {
    if ((e.keyCode || e.charCode) == tabCode) {
        e.preventDefault();
        $(this).closest("form[id^=uploadBannerModal]").find('#body').focus();
    }
}

Keymaps.moveTopicEditor = function (e) {
    if ((e.keyCode || e.charCode) == escCode) {
        var but = $('#move-topic-editor .close:visible')[0]
        if (but) {
            e.preventDefault();
            but.click();
        }
    }
}

Keymaps.defaultDialog = function (e) {
    //disable submit by enter
    if (e.keyCode == enterCode) {
        //if focus on button then do action of button, else click submit
        if (!$(e.target).hasClass('btn')) {
            e.preventDefault();
            jDialog.dialog.find('.btn-primary:first').click();
        }

    }
    if ((e.keyCode || e.charCode) == escCode) {
        jDialog.dialog.find('.close').click();
    }
}

//post,topic,pm forms
Keymaps.bbeditor = function (e) {
    if (e.ctrlKey && e.keyCode == enterCode) {
        e.preventDefault();
        if($('.keymaps-caption:visible').length > 0){
            $('.submit-form').submit();
        }
    }
    //check bb-editor toolbar
    if ($(e.target).parents('form').find('.btn-toolbar').length > 0) {
        //if editor contains button with tooltip Ctrl + <char> than click
        if (e.ctrlKey && e.keyCode >= charMin && e.keyCode <= charMax) {
            var keyVal = String.fromCharCode(e.keyCode);
            var but = $('[data-original-title$="(Ctrl+' + keyVal + ')"]')
            if (but && but.length > 0) {
                e.preventDefault();
                but.click();
            }
        }
    }
}



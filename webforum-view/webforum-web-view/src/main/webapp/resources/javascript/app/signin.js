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
/**
 * Handles login request and displays form with user and password field
 */

$(function () {

    $("#signin").on('click', function (e) {
        // prevent from following link
        e.preventDefault();

        var bodyContent = '\
        '   + Utils.createFormElement($labelUsername, 'userName', 'text', 'first', 'width:90%')
            + Utils.createFormElement($labelPassword, 'password', 'password', null, 'width:90%');

        var footerContent = '<input  type="submit" id="signin-submit-button" value="' + $labelSignin + '" class="btn btn-primary" name="commit"/>';

        var submitDialog = function (e) {
            if (e.keyCode == enterCode) {
                //if focus on username then select password field
                if ($(e.target).is('#userName')) {
                    e.preventDefault();
                    if ($.browser.mozilla) {
                        setTimeout(function () {
                            jDialog.dialog.find('#password').focus();
                        }, 0);
                    }
                    else {
                        jDialog.dialog.find('#password').focus();
                    }
                }
            }
            if ((e.keyCode || e.charCode) == escCode) {
                jDialog.dialog.find('.close').click();
            }
        };

        jDialog.createDialog({
            dialogId: 'signin-modal-dialog',
            title: $labelSignin,
            bodyContent: bodyContent,
            footerContent: footerContent,
            maxWidth: 350,
            tabNavigation: ['#userName', '#password', '#signin-submit-button', 'button.close'],
            handlers: {
                '#signin-modal-dialog': {'submit': sendLoginPost}
            },
            dialogKeydown: submitDialog
        });
    });

    var success = $('#restorePassSuccess');
    if (success.length > 0) {
        var bodyContent = '\
            <div id="restore-passwd" class="control-group"> \
                <h4>' + success.val() + '</h4> \
            </div>';

        var footerContent = '<button id="restore-ok-button" class="btn btn-primary" name="confirm"> \
            ' + $labelOk + '</button>';

        var goToLoginPage = function (e) {
            if (e) {
                e.preventDefault();
            }
            window.location.href = $('.brand').attr('href') + 'login';
        };

        jDialog.createDialog({
            dialogId: 'restore-password-modal-dialog',
            closeDialog: goToLoginPage,
            bodyContent: bodyContent,
            footerContent: footerContent,
            maxWidth: 350,
            tabNavigation: ['#restore-ok-button'],
            handlers: {
                '#restore-ok-button': {'click': goToLoginPage}
            }
        });
    }
});

function sendEmailConfirmation(recipient) {
  $.ajax({
          type: 'GET',
          url: $root + '/confirm?id='+recipient,
          success: function () {
          var message = "";
             if (resp.status == 'SUCCESS') {
                message = $labelEmailConfirmationWasSent;
              } else {
                message = $labelError500Detail
              }
                jDialog.createDialog({
                    type: jDialog.alertType,
                    bodyMessage: $labelEmailConfirmationWasSent
              });
          },
          error: function (jqXHR, textStatus, errorThrown) {
            jDialog.createDialog({
               type: jDialog.alertType,
               bodyMessage: $labelError500Detail
             });
          }
  });
};

/**
 * Handles submit request from login form by sending POST request, with params
 * such as user and password, if "remember me" wasn't checked, for request will be
 * used two params: user and password, otherwise "remember me" param will be appended
 * to request, after successfully login current page will be reloaded, otherwise you will
 * get error message, providing user with opportunity to change login or password
 */
function sendLoginPost(e) {
    e.preventDefault();
    var rememberMeElement = jDialog.dialog.find('input[name=_spring_security_remember_me]');
    var usernameElement = jDialog.dialog.find('#userName');
    var passwordElement = jDialog.dialog.find('#password');

    var remember_me = rememberMeElement.is(':checked');
    var username = usernameElement.val();
    var password = passwordElement.val();

    jDialog.dialog.find('*').attr('disabled', true);

    var query = 'userName=' + encodeURIComponent(username) + '&' + 'password=' + encodeURIComponent(password);
    if (remember_me) {
        query = query + '&_spring_security_remember_me=on';
    }

    $.ajax({
        type: 'POST',
        url: $root + '/login_ajax',
        data: query,
        dataType: 'html',
        success: function (resp) {
            resp = eval('(' + resp + ')');

            if (resp.status == 'SUCCESS') {
                location.reload();
            }
            else {
                if (resp.result && resp.result.customError) {
                    jDialog.createDialog({
                        type: jDialog.alertType,
                        bodyMessage: $labelAuthenticationConnectionError
                    });
                } else {
                    var error_message = '';
                    var userId = resp.result;
                    if (userId == null) {
                        error_message = $labelLoginError;
                    } else {
                        error_message=$labelSendConfirmationEmail
                            .replace("{0}",'<a id="confirm_email_link" href="'+ $root + '/confirm" > ')
                            .replace("{1}","</a>");
                    }

                    jDialog.prepareDialog(jDialog.dialog);

                    ErrorUtils.addErrorStyles('#userName');
                    ErrorUtils.addErrorStyles('#password');

                    passwordElement.val("");
                    passwordElement.parent().append('<span class="help-inline _error">' + error_message + '</span>');
                    jDialog.resizeDialog(jDialog.dialog);
                    jDialog.focusFirstElement();

                    $("#confirm_email_link").on('click', function (e) {
                        e.preventDefault();
                        sendEmailConfirmation(userId);
                    });
                }
            }
        },
        error: function (data) {
            jDialog.createDialog({
                type: jDialog.alertType,
                bodyMessage: $labelError500Detail
            });
        }
    });
};
<%--

    This project is a simple web forum. I created it just to
    demonstrate my programming skills to potential employers.

    Here is short description: ( for more detailed description please reade README.md or
    go to https://github.com/VladimirSharapov/SpringWebForum )

    Front-end: jsp, bootstrap, jquery
    Back-end: Spring, Hibernate
    DB: MySQL and H2(for testing) were used while developing, but the project is database independent.
        Though it must be a relational DB.
    Tools: git,maven,jenkins,nexus,liquibase.

    My LinkedIn profile: https://ru.linkedin.com/in/vladimir-sharapov-6075207

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
  <title>
      <spring:message code="label.signin"/>
  </title>
</head>
<body>

<%-- Container --%>
<div class="container form-login-related">
  <form:form id="login-form" name="form" action='${pageContext.request.contextPath}/login1'
             modelAttribute="loginUserDto" method="POST" class='form-vertical'>
    <fieldset>
      <legend><spring:message code="label.signin"/></legend>

      <div class="control-group">
        <label for="userName" class="control-label"><spring:message code="label.username"/></label>

        <div class="controls">
            <form:input class="reg_input" type="text" path="userName" id="dialog-userName" />
            <%-- Needed to highlight username input when login error occured--%>
            <c:if test="${not empty param.login_error}">
                <span class="help-inline"/>
            </c:if>
        </div>
      </div>

      <div class="control-group">
        <label for="password" class="control-label"><spring:message code="label.password"/> </label>

        <div class="controls">
            <form:input type="password" path="password" id="dialog-password"/>
          <c:if test="${not empty param.login_error}">
            <span class="help-inline show">
              <c:choose>
                <c:when test="${param.login_error == 1}">
                  <spring:message code="label.login_error"/>
                </c:when>
                <c:when test="${param.login_error == 2}">
                  <spring:message code="label.login_cookies_were_theft"/>
                </c:when>
                <c:when test="${param.login_error == 3}">
                  <spring:message code="label.authentication.connection.error"/>
                </c:when>
              </c:choose>
            </span>
          </c:if>
        </div>
      </div>

        <div class="control-group">
            <label class="rememberme-lbl"><input type="checkbox" name="_spring_security_remember_me" class="form-check-radio-box" checked="checked"><spring:message code="label.auto_logon"/></label>
        </div>

        <%-- div>
            <a id="page-signup-link" href="${pageContext.request.contextPath}/user/new"><spring:message
                    code="label.signup.rightnow"/></a>
            <br/>
        </div --%>

      <div class="form-actions">
        <input type="submit" id="page-signin-submit-button" class="btn btn-primary" value="<spring:message code="label.signin"/>"/>
        <br/>

      </div>
    </fieldset>
    <input type="hidden" name="referer" id="referer" value="<c:url value='${referer}'/>" />
  </form:form>
</div>
</body>
</html>

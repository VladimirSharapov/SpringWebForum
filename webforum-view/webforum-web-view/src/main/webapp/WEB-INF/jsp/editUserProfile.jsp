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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<div class="clearfix"></div>
<hr class='user-profile-hr'/>

<div>
    <fieldset>
        <c:choose>
            <c:when test="${param.isCanEditProfile}">
                <div class="control-group">
                    <label class="control-label"><spring:message code="label.firstname"/></label>

                    <div class="controls">
                        <form:input cssClass="input-xlarge"  path="firstName" tabindex="1" cssErrorClass="input-xlarge error"/>
                        <br/>
                        <form:errors path="firstName" cssClass="help-inline"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"><spring:message code="label.lastname"/></label>

                    <div class="controls">
                        <form:input cssClass="input-xlarge" path="lastName" tabindex="5" cssErrorClass="input-xlarge error"/>
                        <br/>
                        <form:errors path="lastName" cssClass="help-inline"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label"><spring:message code="label.firstname"/></label>

                    <div class="controls">
                        <label class="input-xlarge box-label test-firstname">
                            <c:out value='${user.firstName}'/>
                        </label>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"><spring:message code="label.lastname"/></label>

                    <div class="controls">
                        <label class="input-xlarge box-label test-lastname">
                            <c:out value='${user.lastName}'/>
                        </label>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <c:if test="${param.isCanEditProfile}">
            <div class="control-group">
                <label class="control-label"><spring:message code="label.email"/></label>

                <div class="controls">
                    <form:input cssClass="input-xlarge" path="email" tabindex="15" cssErrorClass="input-xlarge error"/><br/>
                    <form:errors path="email" cssClass="help-inline"/>
                </div>
            </div>
        </c:if>

        <div class="control-group">
            <label class="control-label"> <spring:message code="label.registrationDate"/>
            </label>

            <div class="controls">
                <label class="input-xlarge box-label test-registrationdate">
                    <joda:format value="${user.registrationDate}" style="LS"/>
                </label>
                <c:if test="${param.isCanEditProfile}">
                    <form:hidden path="registrationDate"/>
                </c:if>
            </div>
        </div>

    </fieldset>
</div>
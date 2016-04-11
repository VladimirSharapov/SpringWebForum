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
﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
 <sec:authentication property="principal.username" var="auth" scope="request"/>
</sec:authorize>

<head>
    <meta name="description" content="<c:out value="${label.user}"/>">
    <title>
        <spring:message code="label.user"/> - "${user.username}"
    </title>
</head>
<body>


<c:set var="isCanEditProfile" value="false"/>
<c:set var="formAction" value="#"/>

<c:if test="${user.username == auth}">
    <c:set var="isCanEditProfile" value="true"/>
    <c:set var="formAction" value="${pageContext.request.contextPath}/users/${user.id}"/>
</c:if>

<div class="container">

    <div class="user-profile-container">

        <div id="editUserDetails" class="userprofile">

            <form:form id="editProfileForm" name="editProfileForm" action="${formAction}"
                       modelAttribute="user" method="POST" class="form-horizontal">

                <div class='user-profile-header'>
                    <c:if test="${isCanEditProfile}">
                        <%-- form:hidden id="avatar" path="avatar"/ --%>
                        <form:hidden id="editedUserId" path="id"/>
                        <form:hidden id="editedUsername" path="username"/>
                    </c:if>
                        <%--   <span class="pull-left thumbnail">
                             <span id="avatarPreviewContainer" class="wraptocenter">
                              <c:choose>
                                 <c:when test="${isCanEditProfile}">
                                    <%--String prefix "data:image/jpeg;base64," needed for correct image rendering-- %>
                                    <img id="avatarPreview" src="data:image/jpeg;base64,${avatar}" alt=""/>
                                 </c:when>
                                 <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/users/${user.id}/avatar" alt=""/>
                                 </c:otherwise>
                              </c:choose>
                              </span>
                           </span>  --%>


                    <h2 class="pull-left user-profile-username"><span><c:out value="${user.username}"/></span></h2>
                </div>


                <div class="clearfix"></div>

                <div class="clearfix"></div>


                <jsp:include page="editUserProfile.jsp">
                    <jsp:param name="isCanEditProfile" value="${isCanEditProfile}"/>
                </jsp:include>

                <c:if test="${isCanEditProfile}">
                    <hr class='user-profile-hr'/>
                    <div class='user-profile-buttons-form-actions'>
                        <button id="saveChanges" class="btn btn-primary" type="submit" tabindex="60">
                            <spring:message code="label.save_changes"/>
                        </button>
                        <a href="${pageContext.request.contextPath}/users/${user.id}" class="btn space-left-medium" tabindex="60">
                            <spring:message code="label.cancel"/>
                        </a>
                    </div>
                </c:if>
            </form:form>
        </div>
    </div>
</div>
</body>

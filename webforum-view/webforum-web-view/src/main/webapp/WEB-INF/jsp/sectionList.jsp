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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<head>
    <meta name="description" content="<c:out value="${cmpDescription}"/>">

    <title>SpringWebForum</title>
</head>
<body>
<div class="container">
    <div id="foreword" class="row">
        <p>
            1. This project ( just a simple web forum ) is created to demonstrate my programming skills.
               Though it lacks many features, it still demonstrates usage of modern Java related technologies.
               Please read more about the project on <a href="https://github.com/VladimirSharapov/SpringWebForum">GitHub</a>
        </p>
        <p>
            2. To sign in you can use <b>visitor/visitor</b>
        </p>
    </div>
    <div class="row forum-sections-header">

        <h1 class="pull-left logo-text">
            <a class="invisible-link" href="${pageContext.request.contextPath}/"><spring:message
                    code='label.forum'/></a>
        </h1>

        <div class="pull-right">
            <%--
            <span class="forum-sections-header-actions">
               <a href="${pageContext.request.contextPath}/topics/recent" title=""
                  class="forum-sections-recent-unanswered">
                   <spring:message code="label.recent"/>
               </a>
               <br/>
               <a href="${pageContext.request.contextPath}/topics/unanswered" title=""
                  class="forum-sections-recent-unanswered">
                   <spring:message code="label.messagesWithoutAnswers"/>
               </a>
            </span>
            --%>
            <a href="${pageContext.request.contextPath}/topics/recent.rss"
               title="<spring:message code='label.tips.feed_subsription'/>" class="rss-ref">
                <img src="${pageContext.request.contextPath}/resources/images/rss-icon.png" alt="" class="rss-icon">
            </a>
        </div>
    </div>
    <hr class="forum-pagination"/>

    <%-- Sections and branches --%>
    <table id="topics-table" class="table table-row table-with-titles">
        <tbody>
        <c:set var="colspanOfSectionName" value="3"/>

        <c:forEach var="section" items="${sectionList}">
            <tr>
                <td colspan="${colspanOfSectionName}" class="table-title">
                    <h2 class="h-nostyle">
                        <a href="${pageContext.request.contextPath}/sections/${section.id}"><c:out
                                value="${section.name}"/></a>
                    </h2>
                </td>
            </tr>
            <c:forEach var="branch" items="${section.branches}" varStatus="i">
                <tr>
                    <td class="title-col">
                        <div class="pull-left">
                            <h3 class="h-nostyle">
                                <a class="branch-title" href="${pageContext.request.contextPath}/branches/${branch.id}">
                                    <c:out value="${branch.name}"/>
                                </a>
                            </h3>
                                       <span class="forum-sections-branch-description-container"
                                             id='branchDescriptionLabel${branch.id}'>
                                           <c:out value="${branch.description}"/>
                                       </span>
                        </div>
                    </td>

                    <td class="topics-posts shrink-to-fit">
                        <spring:message code="label.section.header.topics"/>:
                        <span class='space-left-small'><c:out value="${branch.topicsCount}"/></span>
                        <br/>
                        <spring:message code="label.section.header.messages"/>:
                        <span class='space-left-small'><c:out value="${branch.postsCount}"/></span>
                    </td>

                    <td class="latest-by shrink-to-fit">
                        <c:if test="${branch.topicsCount > 0}">
                            <i class="icon-calendar"></i>
                            <a class="date last-post-view" href="${pageContext.request.contextPath}/posts/${branch.lastPost.id}#${branch.lastPost.id}">
                                <joda:format value="${branch.lastPost.creationDate}" style="LS"/>
                            </a>

                            <p>
                                <spring:message code="label.topic.last_post_by"/>
                                <a  class="space-left-small user-profile-view"
                                   href="${pageContext.request.contextPath}/users/${branch.lastPost.userCreated.id}"
                                   title="<spring:message code='label.tips.view_profile'/>">
                                    <c:out value="${branch.lastPost.userCreated.username}"/>
                                </a>
                            </p>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${showForumStatus}">
        <div class="well forum-sections-stats-container">
            <strong>
                <spring:message code="label.onlineUsersInfo.messagesCount"/>
            </strong>
        <span class="test-messages">
            <c:out value="${postsCount}"/>
        </span>
            <br/>
            <strong>
                <spring:message code="label.onlineUsersInfo.registeredUsers.count"/>
            </strong>
        <span class="test-registered-users">
            <c:out value="${usersCount}"/>
        </span>
        </div>

        <%-- Users --%>
        <div id="users-stats" class="well forum-sections-userstats-container">
            <strong>
                <spring:message code="label.onlineUsersInfo.visitors"/>
            </strong>
        <span>
            <c:out value="${onlineUsersCount}"/>
        </span>,
        <span class="space-left-small">
            <spring:message code="label.onlineUsersInfo.visitors.registered"/>
        </span>
        <span>
            <c:out value="${registeredUsersCount}"/>
        </span>,
        <span class="space-left-small">
            <spring:message code="label.onlineUsersInfo.visitors.guests"/>
        </span>
        <span>
            <c:out value="${anonymousCount}"/>
        </span>
        </div>
    </c:if>
    <%-- END OF Users --%>
</div>
</body>
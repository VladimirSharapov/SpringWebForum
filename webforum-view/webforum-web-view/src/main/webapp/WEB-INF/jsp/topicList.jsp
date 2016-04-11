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

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="forum" uri="http://www.springforum.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta name="description" content="<c:out value="${branch.name}"/>">

    <title>
        <c:out value="${branch.name}"/> - SpringWebForum
    </title>
    <spring:message code="label.rssFeed" var="branchRssTitle" arguments="${branch.name}" htmlEscape="true"
                    javaScriptEscape="true"/>


</head>
<body>
<div class="container">
    <%-- Branch header --%>
    <div id="branch-header">
        <h1>
            <a class="invisible-link" href="${pageContext.request.contextPath}/branches/${branch.id}">
                <c:out value="${branch.name}"/>
            </a>
        </h1>

        <div id="right-block">
            <a href="${pageContext.request.contextPath}/branches/${branch.id}/recent.rss"
               title="<spring:message code='label.tips.feed_subsription'/>" class="rss-ref">

                <img src="${pageContext.request.contextPath}/resources/images/rss-icon.png" alt="" class="rss-icon">
            </a>
        </div>
        <%--    <span class="inline-block"></span> --%>
    </div>
    <ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/sections" data-original-title=""><spring:message
                code='label.forum'/></a><span class="divider" data-original-title="">/</span></li>
        <li><h3 class="h-nostyle"><a style="font-weight: normal;font-size:13px"
                                     href="${pageContext.request.contextPath}/sections/${section.id}"
                                     data-original-title="">${section.name}</a></h3></li>
    </ul>

    <%-- Upper pagination --%>
    <div class="row-fluid upper-pagination forum-pagination-container">
        <sec:authorize access="isAuthenticated()">
        <div class="new-topic-buttons">
            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/branches/${branch.id}/topic"
               title="<spring:message code='label.topic_btn.tip'/>"
               data-placement="right">
                <spring:message code='label.topic_btn'/>
            </a>
        </div>
        </sec:authorize>
        <div>
            <div class="pagination pull-right forum-pagination">
                <ul>
                    <forum:pagination uri="branches/${branch.id}" page="${topicsPage}"/>
                </ul>
            </div>
        </div>
    </div>
    <%-- END OF Upper pagination --%>
    <spring:message code="label.branch.empty" var="messageToShowIfNoTopics"/>
    <forum:topicList topics="${topics}" messageToShowIfNoTopics='${messageToShowIfNoTopics}'/>
</div>
<%-- END OF Branch header --%>
</body>
</html>

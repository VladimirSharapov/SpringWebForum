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
<%@ taglib prefix="forum" uri="http://www.springforum.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<head>
    <meta name="description" content="<c:out value="${topic.title}"/>">
    <title>
        <c:out value="${topic.title}"/> - SpringWebForum
    </title>
</head>
<body>

<c:set var="authenticated" value="${false}"/>
<div class="container">
    <%-- Topic header --%>
    <div id="branch-header">
        <h1>
            <a class="invisible-link" href="${pageContext.request.contextPath}/topics/${topic.id}">
                <c:out value="${topic.title}"/>
            </a>
        </h1>

        <div id="right-block" style="height:35px"></div>
        <span class='inline-block'></span>
    </div>
    <%-- END OF Topic header --%>

    <ul class="breadcrumb">
        <li>
            <a href="${pageContext.request.contextPath}/sections" data-original-title="">
                <spring:message code='label.forum'/>
            </a>

            <span class="divider" data-original-title="">/</span>
        </li>
        <li>
            <a style="font-weight: normal;font-size:13px"
               href="${pageContext.request.contextPath}/sections/${topic.branch.section.id}"
               data-original-title="">${topic.branch.section.name}</a>

            <span class="divider" data-original-title="">/</span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/branches/${topic.branch.id}" data-original-title="">
                ${topic.branch.name}
            </a>
        </li>
    </ul>

    <%-- Upper pagination --%>
    <div class="row-fluid upper-pagination forum-pagination-container">
        <%-- Pagination --%>

        <div class="pagination pull-right forum-pagination">
            <ul>
                <forum:pagination uri="topics/${topic.id}" page="${postsPage}"/>
            </ul>
        </div>

        <%-- END OF Pagination --%>

    </div>
    <%-- END OF Upper pagination --%>


    <div>
        <%-- List of posts. --%>
        <c:forEach var="post" items="${postsPage.content}" varStatus="i">
            <%-- Post --%>
            <c:set var="isFirstPost" value="true"/>

            <c:set var="postClass" value=""/>
            <c:if test="${isFirstPost}">
                <c:set var="postClass" value="script-first-post"/>
            </c:if>


            <div class="post ${postClass}">
                <div class="anchor">
                    <a id="${post.id}">anchor</a>
                </div>

                <table class="table table-row table-bordered table-condensed">
                    <tr class="post-header">
                        <td class="post-date">
                            <i class="icon-calendar"></i>
                            <joda:format value="${post.creationDate}" style="LS"/>
                        </td>
                        <td class="top-buttons">

                        </td>
                    </tr>
                    <tr class="post-content-tr">
                        <td class="userinfo">
                            <div>
                                <p>
                                    <a class='post-userinfo-username'
                                       href="${pageContext.request.contextPath}/users/${post.userCreated.id}"
                                       title="<spring:message code='label.tips.view_profile'/>">
                                        <c:out value="${post.userCreated.username}"/>
                                    </a>
                                </p>
                            </div>
             
                   <%-- span class="thumbnail post-userinfo-avatal wraptocenter">
                        <img src="${pageContext.request.contextPath}/users/${post.userCreated.id}/avatar" alt=""/>
                   </span --%>

                            <div>
                                <div>
                                    <spring:message code="label.topic.message_count"/>
                                    <span class="space-left-small"><c:out value="${post.userCreated.postCount}"/></span>
                                </div>
                            </div>
                        </td>

                        <td class='post-content-td'>
                            <div class="word-wrap post-content-body">${post.postContent}</div>
                        </td>
                    </tr>
                    <tr class="post-header">
                        <td>
                        </td>
                        <td class="left-border">

                        </td>
                    </tr>
                </table>
            </div>
            <%-- END OF Post --%>
        </c:forEach>
    </div>


    <sec:authorize access="isAuthenticated()">
        <form:form
                action="${pageContext.request.contextPath}/topics/${topic.id}?page=${postsPage.number}#${lastPostId}"
                method="POST" class='well anti-multipost submit-form' modelAttribute="post">
            <div class='control-group controls'>
                <div id="editorBbCodeDiv">
                    <spring:message code="placeholder.editor.content" var="placeHolderContent"/>
                    <form:textarea id="postBody" path="postContent" name="postContent" tabindex="200"
                                   style="width:100%;height:350px"
                                   placeholder="${placeHolderContent}" class="script-confirm-unsaved"
                                   cssErrorClass="error"/>

                </div>

                <form:errors path="postContent" cssClass="help-inline focusToError"/>
            </div>

            <input id="post" type="submit" class="btn btn-primary" accesskey="s" name="post" tabindex="300"
                   value="<spring:message code="label.answer"/>"/>
        </form:form>
    </sec:authorize>

    <script>
        if ($('#bodyText\\.errors:visible').length > 0) {
            Utils.focusFirstEl('#postBody');
        }
    </script>
</body>

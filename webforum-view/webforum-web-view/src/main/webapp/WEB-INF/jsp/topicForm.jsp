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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <meta name="description" content="<c:out value="${branch.name}"/>">
    <title>
        <c:if test="${branch.name != null}">
            <c:out value="${branch.name}"/> -
        </c:if>
        <spring:message code="h.new_topic"/> - SpringWebForum
    </title>
</head>
<body>
<div class="container">
    <ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/sections" data-original-title="">
            <spring:message code='label.forum'/></a>
            <span class="divider" data-original-title="">/</span>
        </li>
        <li>
            <a style="font-weight: normal;font-size:13px"
               href="${pageContext.request.contextPath}/sections/${section.id}"
               data-original-title="">${section.name}</a>

            <span class="divider" data-original-title="">/</span>
        </li>
        <li>
            <a style="font-weight: normal;font-size:13px"
               href="${pageContext.request.contextPath}/branches/${branch.id}"
               data-original-title="">${branch.name}</a>
        </li>
    </ul>


    <form:form action="${pageContext.request.contextPath}/branches/${branch.id}/topic"
               method="POST" modelAttribute="topicPostDto" class="well anti-multipost submit-form">
        <div class='control-group hide-on-preview'>
            <div class='controls'>
                <spring:message code='label.topic.topic_title' var='topicTitlePlaceholder'/>
                <form:input path="topic.title"  type="text" size="45" cssErrorClass="full-width script-confirm-unsaved error"
                            maxlength="255" tabindex="100"
                            class="full-width script-confirm-unsaved" placeholder="${topicTitlePlaceholder}"/>
                <form:errors path="topic.title" id="subjectError" type="text" name="subjectError" size="45"
                             maxlength="255" class="post" cssClass="help-inline focusToError"/>
            </div>
        </div>


        <div class='control-group controls'>
            <div id="editorBbCodeDiv">
                <spring:message code="placeholder.editor.content" var="placeHolderContent"/>
                <form:textarea id="postBody" path="message.postContent" name="postContent" tabindex="200" style="width:100%;height: 350px"
                          placeholder="${placeHolderContent}" class="script-confirm-unsaved" cssErrorClass="error"/>

                <form:errors path="message.postContent" cssClass="help-inline focusToError"/>
            </div>
        </div>

        <input id="post" type="submit" class="btn btn-primary" accesskey="s" name="post" tabindex="300"
               value="<spring:message code="label.send"/>"/>

    </form:form>


</div>
</body>

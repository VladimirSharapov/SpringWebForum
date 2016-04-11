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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>
        <spring:message code="label.500.title"/>
    </title>
</head>
<body>
<div class="container  linear_transitions">
    <div class="text_errorpage pull-left">
        <h1><span class="error_errorpage"><spring:message code="label.error"/></span>&nbsp;500</h1>
        <spring:message code="label.500.detail"/>
        <br/>
        <spring:message code="label.500.refresh"/>&nbsp;
        <a href="${pageContext.request.contextPath}/"><spring:message code="label.back2main"/></a>
    </div>
</div>
<div class="cleared"></div>
</body>
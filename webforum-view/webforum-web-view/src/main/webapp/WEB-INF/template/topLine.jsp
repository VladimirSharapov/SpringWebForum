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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">

            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <span id="logoTooltipHolder" class="hidden"><c:out value="${logoTooltip}"/></span>
            <span id="descriptionHolder" class="hidden"><c:out value="${cmpDescription}"/></span>
            <span id="titlePrefixHolder" class="hidden"><c:out value="${cmpTitlePrefix}"/></span>
            <span id="copyrightHolder" class="hidden"><c:out value="${copyrightTemplate}"/></span>


            <spring:message code="label.forum" var="toolTipKey"/>

            <div class="logo-container" title="${toolTipKey}">
                <a href="${pageContext.request.contextPath}/" title="${toolTipKey}"
                   data-toggle="tooltip" data-placement="right">
                    <img class="forum-logo cursor-pointer" src='${pageContext.request.contextPath}/resources/images/skype.png'
                         alt="${toolTipKey}"/>
                </a>
            </div>

            <a class="brand" href="${pageContext.request.contextPath}/"> <spring:message code="label.forum"/></a>

            <div class="nav-collapse">
                <ul class="nav pull-right">
                    <%--External links start--%>
                    <%-- li id="top-line-links-drop-down" class="dropdown topline-links">
                        <span id="links-toggle" class="dropdown-toggle links-selector-container"
                              data-toggle="dropdown"
                              title='<fmt:message key="label.links"/>'>
                            <fmt:message key="label.links"/>
                           </span>
                        <ul class="dropdown-menu links-menu">
                            <c:if test="${not empty externalLinks}">
                                <c:forEach var="link" items="${externalLinks}">
                                    <li><a id="small-screen-external-link-<c:out value='${link.id}'/>"
                                           data-original-title="<c:out value='${link.hint}'/>"
                                           href="<c:out value='${link.url}'/>">
                                        <c:out value="${link.title}"/>
                                    </a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </li --%>


                    <sec:authorize access="isAnonymous()">
                    <%-- li>
                        <a id="top-signup-link" href="${pageContext.request.contextPath}/user/new">
                            <fmt:message key="label.signup"/>
                        </a>
                    </li --%>
                    <li class="divider-vertical"></li>
                    <li>
                        <a id="signin" href="${pageContext.request.contextPath}/login">
                            <fmt:message key="label.signin"/>
                        </a>
                    </li>
                    </sec:authorize>
                    <%-- END OF Not logged in block --%>

                    <%-- Logged in block --%>
                    <sec:authorize access="isAuthenticated()">

                        <sec:authentication property="principal.username" var="username" scope="request"/>
                        <sec:authentication property="principal.id" var="userId" scope="request"/>

                        <li class="dropdown">
                        <div class="dropdown-toggle topline-dropdown-menu" data-toggle="dropdown">
                            <a id="user-dropdown-menu-link" href="#">
                                <c:out value="${username}"/>
                            </a>
                            <b class="caret"></b>
                        </div>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${pageContext.request.contextPath}/users/${userId}" id="user-menu-profile">
                                    <fmt:message key="label.profile"/>
                                </a>
                            </li>


                            <li>
                                <a href="${pageContext.request.contextPath}/logout" id="user-menu-logout">
                                    <fmt:message key="label.logout"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    </sec:authorize>
                    <%-- END OF Logged in block --%>
                    <%-- div id="formsPlaceholder"/ --%>
                    <%-- Language chooser --%>
                    <%-- Language chooser --%>
                    <%-- sec:authorize access="permitAll()" --%>
                    <li id="lang-selector-toggle-li" class="dropdown">
                        <div id="lang-selector-toggle" class="dropdown-toggle topline-dropdown-menu"
                             data-toggle="dropdown"
                             title="<fmt:message key='label.click_language'/>">
                            <a href="#" id="languages-menu">
                                <img src="${pageContext.request.contextPath}/resources/images/flags/<fmt:message key='locale.code'/>.png"
                                     alt="<fmt:message key='locale.name'/>"/>
                            </a>
                            <b class="caret"></b>
                        </div>
                        <ul class="dropdown-menu lang-menu">
                            <li id='lang-en'>
                                <a href="${pageContext.request.requestURL}?lang=en">
                                    <img src="${pageContext.request.contextPath}/resources/images/flags/en.png"
                                         alt="<fmt:message key='label.english'/>"/>
                                    <fmt:message key='label.english'/>
                                </a>
                            </li>
                            <li id='lang-ru'>
                                <a href="${pageContext.request.requestURL}?lang=ru">
                                    <img src="${pageContext.request.contextPath}/resources/images/flags/ru.png"
                                         alt="<fmt:message key='label.russian'/>"/>
                                    <fmt:message key='label.russian'/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <%-- /sec:authorize --%>
                    <%-- END OF Language chooser --%>
                </ul>

            </div>
        </div>
    </div>
</div>



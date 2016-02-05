<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
  <meta name="description" content="<c:out value="${cmpDescription}"/>">
  <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="/resources/css/forum.css">

  <title>
    <c:out value="${cmpTitlePrefix}"/>
    <c:out value="${cmpDescription}"/>
  </title>
</head>
<body>
<div class="container">

  <%-- Sections and branches --%>
  <table id="topics-table" class="table table-row table-with-titles">
    <tbody>
    <c:set var="colspanOfSectionName" value="3"/>

    <c:forEach var="section" items="${sectionList}">
      <tr>
        <td colspan="${colspanOfSectionName}" class="table-title">
          <h2 class="h-nostyle">
            <a href="${pageContext.request.contextPath}/sections/${section.id}"><c:out value="${section.name}"/></a>
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
                                       <span class="forum-sections-branch-description-container" id='branchDescriptionLabel${branch.id}'>
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
              <a class="date" href="${pageContext.request.contextPath}/posts/${branch.lastPost.id}">
                <c:out value="${branch.lastPost.creationDate}"/>
              </a>

              <p>
                <spring:message code="label.topic.last_post_by"/>
                <a class="space-left-small"
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
</div>
</body>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="uri" required="true" type="java.lang.String" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>

<%--Set default value for numberLink attribute, if it it wasn't passed.--%>
<c:if test="${empty numberLink}">
    <c:set var="numberLink" value="3"/>
</c:if>

<c:if test="${page.number > 0}">
    <li>
        <a href="${pageContext.request.contextPath}/${uri}" title="<spring:message code='pagination.first'/>">
            <i class="icon-chevron-left"></i>
        </a>
    </li>
</c:if>


<c:forEach var="i" begin="1" step="1" end="${numberLink}">
    <%--JSTL doesn't have reverse for-each, therefore this trick used.--%>
    <c:set var="j" value="${numberLink - i + 1}"/>
    <c:if test="${page.number + 1 > j}">
        <c:set var="p" value="${page.number - j}"/>
        <li><a href="${pageContext.request.contextPath}/${uri}?page=${p}">${page.number - j + 1}</a></li>
    </c:if>
</c:forEach>

<%--Link to current page is disabled.--%>
<c:if test="${page.totalPages > 1}">
    <li class='active'><a href='#'><c:out value="${page.number + 1}"/></a></li>
</c:if>

<c:forEach var="i" begin="0" step="1" end="${numberLink - 1}">
    <c:if test="${page.number + i + 1 < page.totalPages}">
        <c:set var="p" value="${page.number + i + 1}"/>
        <li><a href="${pageContext.request.contextPath}/${uri}?page=${p}">${page.number + i + 2}</a></li>
    </c:if>
</c:forEach>

<c:if test="${page.number + 1 < page.totalPages }">
    <li>
        <a href="${pageContext.request.contextPath}/${uri}?page=${page.totalPages - 1}" title="<spring:message code='pagination.last'/>">
            <i class="icon-chevron-right"></i>
        </a>
    </li>
</c:if>

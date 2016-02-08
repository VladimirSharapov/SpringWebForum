<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="en"/>
<!DOCTYPE HTML>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/forum.css">

  <decorator:head/>

</head>
<body>
  <decorator:body></decorator:body>
  <div class="container">
    <footer>
      <div>
        <div class="pull-left">
          <c:choose>
            <c:when test="${sessionScope.adminMode == true}">
              <span id="userDefinedCopyright" class="cursor-pointer"><c:out value='${userDefinedCopyright}'/></span>
            </c:when>
            <c:otherwise>
              <c:out value='${userDefinedCopyright}'/>
            </c:otherwise>
          </c:choose><br/>
          Powered by Vladimir Sharapov ${project.version} by <a class="space-left-small" href="http://shv.org">shv.org</a><br/>
          Design with <a class="space-left-small" href="http://getbootstrap.com">Twitter Bootstrap</a>
        </div>

      </div>
      <c:if test="${not empty sapeLinks}">
        <div class="sape-div">
          <%--this shouldn't be escaped because we receive HTML elements from SAPE which should be shown as is--%>
          ${sapeLinks}
        </div>
      </c:if>
    </footer>
  </div>

</body>
</html>

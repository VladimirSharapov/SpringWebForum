<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="en"/>
<!DOCTYPE HTML>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/forum.css">
  <script>
    $localeCode    = '<spring:message code="locale.code"    htmlEscape="true"/>';
    $labelUsername = '<spring:message code="label.username" htmlEscape="true"/>';
    $labelPassword = '<spring:message code="label.password" htmlEscape="true"/>';
    $labelSignin   = '<spring:message code="label.signin"   htmlEscape="true"/>';
  </script>

  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/jquery-1.7.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/jquery.truncate.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/jquery-ui.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/jquery-ui-i18n.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/jquery.prettyPhoto.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/contextmenu/jquery.contextMenu.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/contextmenu/jquery-fieldselection.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/jquery/contextmenu/textarea-helper.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/lib/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/app/topline.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/app/antimultipost.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/app/global.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/app/utils.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/app/keymaps.js"></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/app/dialog.js"></script>
  <%-- script src="${pageContext.request.contextPath}/resources/javascript/app/signin.js"></script --%>


  <decorator:head/>

</head>
<body>
  <jsp:include page="../template/topLine.jsp"/>
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

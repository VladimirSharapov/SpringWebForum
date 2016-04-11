<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<%@ attribute name="topics" required="true" type="java.util.List" %>
<%@ attribute name="messageToShowIfNoTopics" required="true" type="java.lang.String" rtexprvalue="true" %>
<%@ attribute name="showBranchColumn" required="false" type="java.lang.Boolean" rtexprvalue="true" %>

<table id="topics-table" class="table table-row table-bordered">
    <c:choose>
        <c:when test="${!(empty topics)}">
            <thead>
            <tr>
                <th><spring:message code="label.branch.header.topics"/></th>
                <th class="latest-by forum-latest-by-header">
                    <spring:message code="label.branch.header.lastMessage"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="topic" items="${topics}">
                <tr>
                    <td class="posts-td-small posts-td-small_2">
                        <h2 class="h-nostyle">
                            <a class="space-left-small-nf"
                               href="${pageContext.request.contextPath}/topics/${topic.id}">
                                <c:out value="${topic.title}"/>
                            </a>
                        </h2>

                        <div class="created-by">
                            <span><spring:message code="label.topic.created_by"/>&nbsp;</span>
                            <a href="${pageContext.request.contextPath}/users/${topic.topicStarter.id}"
                               data-original-title="<spring:message code="label.tips.view_profile"/>"
                               data-placement="right">
                                <c:out value="${topic.topicStarter.username}"/>
                            </a>&nbsp;&nbsp;
                        </div>
                    </td>
                    <td class="latest-by shrink-to-fit">
                        <c:if test="${not empty topic.lastPost}">

                        <div>
                            <i class="icon-calendar"></i>
                            <a  class="date margin-right-big last-post-view"
                               href="${pageContext.request.contextPath}/posts/${topic.lastPost.id}#${topic.lastPost.id}"
                               data-original-title="<spring:message code="label.branch.header.lastMessage.tooltip"/>">
                                <joda:format value="${topic.lastPost.creationDate}" style="LS" />
                            </a>
                            <i class="icon-envelope margin-left-big margin-right-big"></i>
                            <span class='test-views' data-original-title="<spring:message code="label.branch.header.posts"/>">
                                <c:out value="${topic.postCount}"/>
                            </span>
                        </div>
                        <div>
                            <i class="icon-user"></i>
                            <a  class="space-left-small user-profile-view"
                               href="${pageContext.request.contextPath}/users/${topic.lastPost.userCreated.id}"
                               title="<spring:message code='label.tips.view_profile'/>">
                                <c:out value="${topic.lastPost.userCreated.username}"/>
                            </a>
                        </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </c:when>
        <c:otherwise>
            <tbody>
            <tr>
                <td><c:out value="${messageToShowIfNoTopics}"/></td>
            </tr>
            </tbody>
        </c:otherwise>
    </c:choose>
</table>

<html>
<%@include file="/resources/shared/head.jsp" %>
<head>
    <title>Admin | User Manage</title>
</head>
<body class="is-preload">
<div id="wrapper">
    <%@include file="/resources/shared/admin-header.jsp" %>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <h1>User Manage</h1>
            <div class="field">
                <ul class="actions justify-content-end">
                    <li><input type="submit" value="Add New User" class="primary"
                               onclick="window.location.href='add-user'; return false;"/></li>
                </ul>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">User Id</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty users}">
                            <c:forEach items="${users}" var="user">
                                <c:url var="updateLink" value="/admin/user/update-user">
                                    <c:param name="userId" value="${user.id}"/>
                                </c:url>
                                <c:url var="deleteLink" value="/admin/user/remove-user">
                                    <c:param name="userId" value="${user.id}"/>
                                </c:url>
                                <tr>
                                    <th class="align-middle" scope="row">${user.id}</th>
                                    <td class="align-middle">${user.fullName}</td>
                                    <td class="align-middle">${user.email}</td>
                                    <td class="align-middle">
                                        <ul class="icons mb-0">
                                            <li><a href="${updateLink}"
                                                   class="icon style2 fa-edit"><span
                                                    class="label">Edit</span></a></li>
                                            <li><a href="${deleteLink}" class="icon style2 fa-remove"
                                                   onclick="if (!confirm('Are you sure you want to delete?')) return false;"><span
                                                    class="label">Remove</span></a></li>
                                        </ul>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center">No data</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/resources/shared/scripts.jsp" %>
</body>
</html>

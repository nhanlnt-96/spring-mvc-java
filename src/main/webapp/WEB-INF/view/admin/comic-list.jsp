<html>
<%@include file="/resources/shared/head.jsp" %>
<head>
    <title>Admin | Comic Manage</title>
</head>
<body class="is-preload">
<div id="wrapper">
    <%@include file="/resources/shared/admin-header.jsp" %>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <h1>Comic Manage</h1>
            <div class="field">
                <ul class="actions justify-content-end">
                    <li><input type="submit" value="Add New Comic" class="primary"
                               onclick="window.location.href='add-comic'; return false;"/></li>
                </ul>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Comic Id</th>
                        <th scope="col">Comic Cover Image</th>
                        <th scope="col">Comic Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Created At</th>
                        <th scope="col">Updated At</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty comics}">
                            <c:forEach items="${comics}" var="comic">
                                <c:url var="updateLink" value="/admin/comic/update-comic">
                                    <c:param name="comicId" value="${comic.id}"/>
                                </c:url>
                                <c:url var="deleteLink" value="/admin/comic/remove-comic">
                                    <c:param name="comicId" value="${comic.id}"/>
                                </c:url>
                                <tr>
                                    <th class="align-middle" scope="row">${comic.id}</th>
                                    <td class="align-middle">
                                        <div style="max-width: 100px; width: 100%; margin: 0 auto;">
                                            <div style="width: 100%; padding-bottom: 100%; position: relative">
                                                <img src="${comic.imageCover}" alt="${comic.comicName}"
                                                     style="position: absolute; width: 100%; height: 100%; object-fit: contain; left: 0; top: 0;">
                                            </div>
                                        </div>
                                    </td>
                                    <td class="align-middle">${comic.comicName}</td>
                                    <td class="align-middle">${comic.description}</td>
                                    <td class="align-middle">${comic.createdAt}</td>
                                    <td class="align-middle">${comic.updatedAt}</td>
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
                                <td colspan="7" class="text-center">No data</td>
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

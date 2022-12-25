<html>
<%@include file="/resources/shared/head.jsp" %>
<head>
    <title>Admin | Category Manage</title>
</head>
<body class="is-preload">
<div id="wrapper">
    <%@include file="/resources/shared/admin-header.jsp" %>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <h1>Category Form</h1>
            <form:form action="save-category" modelAttribute="category" method="post">
                <form:hidden path="id"/>
                <div class="fields">
                    <div class="field half">
                        <form:input path="categoryName" placeholder="Category Name"/>
                        <form:errors path="categoryName" cssClass="alert-danger"/>
                    </div>

                    <c:if test="${not empty duplicateCategoryError}">
                        <div class="field d-flex justify-content-center align-items-center">
                            <span class="alert alert-danger">${duplicateCategoryError}</span>
                        </div>
                    </c:if>

                    <div class="field">
                        <ul class="actions justify-content-center">
                            <li>
                                <input type="submit" value="Submit" class="primary"/>
                            </li>
                        </ul>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<%@include file="/resources/shared/scripts.jsp" %>
</body>
</html>

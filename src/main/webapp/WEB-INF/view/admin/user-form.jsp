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
            <h1>User Form</h1>
            <form:form action="save-user" modelAttribute="user" method="post">
                <form:hidden path="id"/>
                <div class="fields">
                    <div class="field half">
                        <form:input path="fullName" placeholder="Full Name"/>
                        <form:errors path="fullName" cssClass="alert-danger"/>
                    </div>

                    <div class="field half">
                        <form:input path="email" placeholder="Email"/>
                        <form:errors path="email" cssClass="alert-danger"/>
                    </div>

                    <div class="field half">
                        <form:password path="password" placeholder="Password"/>
                        <form:errors path="password" cssClass="alert-danger"/>
                    </div>

                    <c:if test="${not empty duplicateEmailError}">
                        <div class="field d-flex justify-content-center align-items-center">
                            <span class="alert alert-danger">${duplicateEmailError}</span>
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

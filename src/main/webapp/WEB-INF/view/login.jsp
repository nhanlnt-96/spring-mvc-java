<html>
<%@include file="/resources/shared/head.jsp" %>
<head>
    <title>Admin | Login</title>
</head>
<body>
<div id="wrapper" style="width: 100vw;min-height: 100vh" class="d-flex justify-content-center align-items-center">
    <!-- Main -->
    <div id="main" style="width: 100%;padding-bottom: 0">
        <div class="inner">
            <h1>Login</h1>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="fields">
                    <div class="field half">
                        <input type="text" name="username" placeholder="Email"/>
                    </div>

                    <div class="field half">
                        <input type="password" name="password" placeholder="Password"/>
                    </div>

                    <c:if test="${param.error}">
                        <div class="field d-flex justify-content-center align-items-center">
                            <span class="alert alert-danger">${param.error}</span>
                        </div>
                    </c:if>

                    <div class="field">
                        <ul class="actions justify-content-center">
                            <li>
                                <input type="submit" value="Login" class="primary"/>
                            </li>
                        </ul>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/resources/shared/scripts.jsp" %>
</body>
<!-- Header -->
<header id="header">
    <div class="inner">
        <!-- Logo -->
        <a href="<c:url value="/admin/comic/comic-list"/>" class="logo">
            <span class="fa fa-book"></span> <span class="title">Free Comic Online Website | Admin</span>
        </a>
        <!-- Nav -->
        <nav>
            <ul>
                <li class="d-flex justify-content-center align-items-center">
                    <span style="margin-right: 6px">Hello, <sec:authentication property="principal.username"/></span>
                    <form:form action="${pageContext.request.contextPath}/logout" method="post"
                               cssStyle="margin-bottom: 0;font-size: initial">
                        <input type="submit" value="Logout" style="line-height: 1em">
                    </form:form>
                </li>
                <li><a href="#menu">Menu</a></li>
            </ul>
        </nav>
    </div>
</header>

<!-- Menu -->
<nav id="menu">
    <h2>Menu</h2>
    <ul>
        <li><a href="<c:url value="/admin/comic/comic-list"/>">Comic Manage</a></li>
        <li><a href="<c:url value="/admin/category/category-list"/>">Category Manage</a></li>
        <li><a href="<c:url value="/admin/user/user-list"/>">User Manage</a></li>
    </ul>
</nav>
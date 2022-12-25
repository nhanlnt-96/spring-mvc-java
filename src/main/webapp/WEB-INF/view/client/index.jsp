<html>
<%@include file="/resources/shared/head.jsp" %>
<head>
    <title>Free Comic Online Website</title>
</head>
<body class="is-preload">
<div id="wrapper">
    <%@include file="/resources/shared/client-header.jsp" %>

    <div id="main">
        <div class="inner">
            <h1>Comics</h1>

            <div class="image main">
                <img src="<c:url value="/resources/images/banner-image-6-1920x500.jpg"/>" class="img-fluid" alt=""/>
            </div>

            <section class="tiles">
                <c:forEach items="${comics}" var="comic">
                    <article class="style2">
                        <span class="image">
                            <img src="${comic.imageCover}" alt="${comic.comicName}"/>
                        </span>
                        <a href="<c:url value="/comic-detail?comicId=${comic.id}"/>">
                            <h2>${comic.comicName}</h2>

                            <p>${comic.category.categoryName} &nbsp;|&nbsp; ${comic.createdAt}</p>

                            <p>${comic.description}</p>
                        </a>
                    </article>
                </c:forEach>
            </section>
        </div>
    </div>
</div>

<%@include file="/resources/shared/scripts.jsp" %>
</body>
</html>

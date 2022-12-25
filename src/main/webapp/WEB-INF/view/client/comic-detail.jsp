<html>
<%@include file="/resources/shared/head.jsp" %>
<head>
    <title>${comic.comicName}</title>
</head>
<body class="is-preload">
<div id="wrapper">
    <%@include file="/resources/shared/client-header.jsp" %>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <h1>${comic.comicName}</h1>

            <div class="image main">
                <div style="width: 100%; padding-bottom: calc(490 / 1344 * 100%); position: relative">
                    <img src="${comic.imageCover}" class="img-fluid" alt="${comic.comicName}"
                         style="position: absolute; width: 100%; height: 100%; left: 0; right: 0; object-fit: contain"/>
                </div>
            </div>

            ${comic.comicContent}
        </div>
    </div>
</div>

<%@include file="/resources/shared/scripts.jsp" %>
</body>
</html>

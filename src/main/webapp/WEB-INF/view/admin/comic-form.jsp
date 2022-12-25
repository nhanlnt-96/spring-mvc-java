<!DOCTYPE HTML>
<html>
<%@include file="/resources/shared/head.jsp" %>
<head>
    <title>Admin | Comic Manage</title>
    <script src="https://cdn.tiny.cloud/1/q66ypois35xr9afbqu95grrd0jwqvkfyd5rts3oktgod8kq4/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>
</head>
<body class="is-preload">
<div id="wrapper">
    <%@include file="/resources/shared/admin-header.jsp" %>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <h1>Comic Form</h1>
            <form:form method="post" action="save-comic" modelAttribute="comic">
                <c:if test="${not empty duplicateComicError}">
                    <div class="field d-flex justify-content-center align-items-center">
                        <span class="alert alert-danger">${duplicateComicError}</span>
                    </div>
                </c:if>

                <form:hidden path="id"/>

                <div class="fields">
                    <div class="field half">
                        <form:select path="categorySelected">
                            <form:option value="NONE" label="Select Category"/>
                            <form:options items="${categories}" itemValue="id" itemLabel="categoryName"/>
                        </form:select>
                        <form:errors path="categorySelected" cssClass="alert-danger"/>
                    </div>

                    <div class="field half">
                        <form:input path="comicName" placeholder="Comic Name"/>
                        <form:errors path="comicName" cssClass="alert-danger"/>
                    </div>

                    <div class="field half">
                        <form:input path="description" placeholder="Description"/>
                        <form:errors path="description" cssClass="alert-danger"/>
                    </div>

                    <div class="field">
                        <form:textarea rows="5" placeholder="Comic Content" path="comicContent"/>
                        <form:errors path="comicContent" cssClass="alert-danger"/>
                    </div>

                    <div class="field half">
                        <label class="upload-file">
                            <input type="file" id="fileInput"/>
                            <form:hidden path="imageCover" id="imageInput"/>
                            <a class="icon style2 fa-upload">Upload</a>
                        </label>
                        <form:errors path="imageCover" cssClass="alert-danger"/>
                    </div>

                    <div id="imagePreview" class="field half">
                        <div class="progress d-none" id="uploadImageProgressContainer">
                            <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar"
                                 id="uploadImageProgress"></div>
                        </div>
                    </div>

                    <div class="field">
                        <ul class="actions justify-content-center">
                            <li>
                                <input type="submit" value="Submit" class="primary" id="submitCreateComicBtn"/>
                            </li>
                        </ul>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<%@include file="/resources/shared/scripts.jsp" %>

<%--handle init tiny editor--%>
<script>
    tinymce.init({
        selector: '#comicContent',
        menubar: false,
        plugins: [
            'advlist',
            'autolink',
            'lists',
            'link',
            'image',
            'charmap',
            'preview',
            'anchor',
            'searchreplace',
            'visualblocks',
            'code',
            'fullscreen',
            'insertdatetime',
            'media',
            'table',
            'code',
            'help',
            'wordcount'
        ],
        toolbar:
            'undo redo | blocks fontsize | bold italic forecolor | alignleft aligncenter alignright alignjustify lineheight | bullist numlist outdent indent | charmap table removeformat code image media link | fullscreen preview',
    });
</script>

<%--handle init update data--%>
<script>
    $(document).ready(() => {
        const imageInputValue = $("#imageInput").val()

        if (imageInputValue) {
            document.getElementById("imagePreview").insertAdjacentHTML('beforeend', `
                <div class="w-100">
                    <label class="form-label">Image preview</label>
                    <div class="w-100" style="padding-bottom: 100%; position:relative;">
                        <img src="` + imageInputValue + `"  alt="image-preview" style="position:absolute;width: 100%;height: 100%;left: 0;top: 0;object-fit: contain"/>
                    </div>
                </div>
            `)
        }
    })
</script>

<%--handle upload image--%>
<script type="module">
    import {initializeApp} from 'https://www.gstatic.com/firebasejs/9.14.0/firebase-app.js'
    import {
        getStorage,
        ref,
        uploadBytesResumable,
        getDownloadURL
    } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-storage.js"
    import env from "<c:url value="/resources/assets/.env.json"/>" assert {type: "json"}

    const firebaseConfig = {
        apiKey: env.API_KEY,
        authDomain: env.AUTH_DOMAIN,
        projectId: env.PROJECT_ID,
        storageBucket: env.STORAGE_BUCKET,
        messagingSenderId: env.MESSAGING_SENDER_ID,
        appId: env.APP_ID
    }

    const app = initializeApp(firebaseConfig)
    const storage = getStorage(app)

    $(document).ready(() => {
        $("#fileInput").change((e) => {
            const imageFile = e.target.files[0]
            const imageTypes = ['apng', 'apng', 'gif', 'jpg', 'jpeg', 'jfif', 'pjpeg', 'pjp', 'png', 'svg', 'webp']

            if (!imageTypes.includes(imageFile?.type?.replace("image/", ""))) {
                alert("Image upload incorrect content type. Require: " + imageTypes.join('/'))
                $("#imageInput").val("")
            } else {
                // disable submit button
                $("#submitBtn").attr("disabled", true);
                // Upload file and metadata to the object 'images/mountains.jpg'
                const storageRef = ref(storage, 'images/' + (new Date() + "-" + imageFile.name).replaceAll(" ", "-"));
                const uploadTask = uploadBytesResumable(storageRef, imageFile, {
                    contentType: imageFile.type
                });

                // Listen for state changes, errors, and completion of the upload.
                uploadTask.on('state_changed',
                    (snapshot) => {
                        // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
                        const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                        const uploadImageProgress = $("#uploadImageProgress")
                        $("#uploadImageProgressContainer").removeClass("d-none")
                        uploadImageProgress.css('width', Math.floor(progress) + '%')
                        uploadImageProgress.text(Math.floor(progress) + '%')
                    },
                    (error) => {
                        console.log(error)
                    },
                    () => {
                        // Upload completed successfully, now we can get the download URL
                        getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
                            $("#preview-image-before-upload").addClass("d-none")
                            $("#uploadImageProgressContainer").addClass("d-none")
                            $("#imagePreview").html(`
                                <div class="w-100">
                                    <label class="form-label">Image preview</label>
                                    <div class="w-100" style="padding-bottom: 100%; position:relative;">
                                        <img src="` + downloadURL + `"  alt="image-preview" style="position:absolute;width: 100%;height: 100%;left: 0;top: 0;object-fit: contain"/>
                                    </div>
                                </div>
                            `)

                            $("#imageInput").val(downloadURL)
                        });
                    }
                );
            }
        })
    })
</script>
</body>
</html>

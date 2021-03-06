<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Post</title>
</head>
<body>
<center>
    <p>
        <span id="successMessage"><b>${messages.NewPost}</b><b>${messages.upload}</b></span>
    </p>
    <h1>upload image</h1>
    <form method="post" action="upload" enctype="multipart/form-data">
        Choose a file:
        <input type="file" name="uploadFile"/>
        <br/><br/>
        <input type="submit" value="Upload" />
    </form>

    <h1>Create a post</h1>
    <form action="postcreate" method="post">
        <table border="2">
            <tr>
                <td>Title</td>
                <td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>Picture</td>
                <td>
                    <c:if test="${filePath != null}">
                        <img src="${filePath}" width="100px">
                    </c:if>
                    <input type="text" name="picture" value="${filePath}" hidden>
                </td>
            </tr>
            <tr>
                <td>Content</td>
                <td><input type="text" name="content"></td>
            </tr>
            <tr>
                <td align="center" colspan="2"><input type="submit" value="POST"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Find Duplicates</title>
</head>
<body>
<%
    if(request.getParameter("message")!=null&&request.getParameter("message").equals("Please have a valid file input.")){
        out.println("<script>alert('"+request.getParameter("message")+"')</script>");
    }
%>
<jsp:include page="shared/navbar.jsp"></jsp:include>
<main role="main">
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">Find the duplicates!</h1>
            <p>This is a webapp which is used to find the duplicates or potential duplicates by
                Double Metaphone and Levenshtein distance algorithms. Only CSV files accepted.</p>
            <p style="color: red"> ** Please use files in root directory.</p>
            <br>
            <form method="post" action="/Upload" enctype="multipart/form-data">
                <div class="custom-file">
                    <input class="btn btn-secondary" type="file" name="uploadedFile" id="uploadedFile" value="Browse">
                </div>
                <br><br>
                <input class="btn btn-primary btn-lg" role="button" type="submit" value="Find it!">
            </form>
        </div>
    </div>
</main>
<jsp:include page="shared/footer.jsp"></jsp:include>
</body>
</html>

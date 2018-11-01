<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<jsp:include page="shared/navbar.jsp"></jsp:include>
<main role="main">
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <h3>Potential duplicates</h3>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">FirstName</th>
            <th scope="col">LastName</th>
            <th scope="col">Company</th>
            <th scope="col">Email</th>
            <th scope="col">Address1</th>
            <th scope="col">Address2</th>
            <th scope="col">Zip</th>
            <th scope="col">City</th>
            <th scope="col">State_long</th>
            <th scope="col">State</th>
            <th scope="col">Phone</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${duplicates}" var="duplicate">
            <tr>
                <th scope="row"><c:out value="${duplicate.getId()}"/></th>
                <td><c:out value="${duplicate.getFirstName()}"/></td>
                <td><c:out value="${duplicate.getLastName()}"/></td>
                <td><c:out value="${duplicate.getCompany()}"/></td>
                <td><c:out value="${duplicate.getEmail()}"/></td>
                <td><c:out value="${duplicate.getAddress1()}"/></td>
                <td><c:out value="${duplicate.getAddress2()}"/></td>
                <td><c:out value="${duplicate.getZip()}"/></td>
                <td><c:out value="${duplicate.getCity()}"/></td>
                <td><c:out value="${duplicate.getState_long()}"/></td>
                <td><c:out value="${duplicate.getState()}"/></td>
                <td><c:out value="${duplicate.getPhone()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <h3>Non duplicates</h3>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">FirstName</th>
            <th scope="col">LastName</th>
            <th scope="col">Company</th>
            <th scope="col">Email</th>
            <th scope="col">Address1</th>
            <th scope="col">Address2</th>
            <th scope="col">Zip</th>
            <th scope="col">City</th>
            <th scope="col">State_long</th>
            <th scope="col">State</th>
            <th scope="col">Phone</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${nonDuplicates}" var="nonDuplicate">
            <tr>
                <th scope="row"><c:out value="${nonDuplicate.getId()}"/></th>
                <td><c:out value="${nonDuplicate.getFirstName()}"/></td>
                <td><c:out value="${nonDuplicate.getLastName()}"/></td>
                <td><c:out value="${nonDuplicate.getCompany()}"/></td>
                <td><c:out value="${nonDuplicate.getEmail()}"/></td>
                <td><c:out value="${nonDuplicate.getAddress1()}"/></td>
                <td><c:out value="${nonDuplicate.getAddress2()}"/></td>
                <td><c:out value="${nonDuplicate.getZip()}"/></td>
                <td><c:out value="${nonDuplicate.getCity()}"/></td>
                <td><c:out value="${nonDuplicate.getState_long()}"/></td>
                <td><c:out value="${nonDuplicate.getState()}"/></td>
                <td><c:out value="${nonDuplicate.getPhone()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<jsp:include page="shared/footer.jsp"></jsp:include>
</body>
</html>

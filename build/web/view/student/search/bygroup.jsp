

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group</title>
        <style>
		body {
			background-color: #FFFFFF;
			color: #333333;
		}

		table {
			border: 1px solid #CCCCCC;
			border-collapse: collapse;
			width: 100%;
		}

		th {
			background-color: #F27125;
			color: #FFFFFF;
			font-weight: normal;
			padding: 8px;
			text-align: left;
			border: 1px solid #CCCCCC;
		}

		td {
			padding: 8px;
			border: 1px solid #CCCCCC;
		}

		tr:nth-child(even) {
			background-color: #F9F9F9;
		}
            #top-right {
			position: absolute;
			top: 0;
			right: 0;
			padding: 10px;
		}
        </style>
    </head>
    <body>
        <a id="top-right" href="http://localhost:9999/SE1722_PRJ301_Assignment/logout" style="color: #123456; font-size: 30px">LOGOUT</a>
        <form action="group" method="GET">
            <c:forEach items="${requestScope.groups}" var="g">
                <input type="checkbox"
                       <c:forEach items="${requestScope.groupIDs}" var="groupID">
                           <c:if test="${g.id eq groupID}">   
                               checked="checked"
                           </c:if>
                       </c:forEach>
                       name="groupID" value="${g.id}"/>  <span style="color: #F27125;">${g.name}</span> 
            </c:forEach>
            <br/>
            <input type="submit" style="background-color: #F27125; color: white; border: none; padding: 10px 20px; border-radius: 5px;" value="Search"/>
        </form>
        <c:if test="${requestScope.students ne null}">
            <table border="1px" style="background-color: #F27125"> 
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Gender</td>
                    <td>Dob</td>
                    <td>Gmail</td>
                    <td>Phone Number</td>
                    <td>Group</td>
                </tr>
                <c:forEach var="student" items="${students}" varStatus="loop">
            <tr <c:if test="${loop.index mod 2 ne 0}">style="background-color: #D3D3D3;"</c:if>>
                <td>${student.getId()}</td>
                <td>${student.getName()}</td>
                <td>
                    <c:if test="${student.isGender()}">Male</c:if>
                    <c:if test="${!student.isGender()}">Female</c:if>
                 <td>
                <fmt:formatDate type = "date" 
         value = "${student.getDob()}" />
                    </td>
                <td>${student.getGmail()}</td>
                <td>${student.getPhone()}</td>
                <td>
                    <c:forEach var="group" items="${student.getGroups()}">
                        ${group.getName()}<br>
                    </c:forEach>
                        </c:forEach>
    </table>
</c:if>
</body>
</html>
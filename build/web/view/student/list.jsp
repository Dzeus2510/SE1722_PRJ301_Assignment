
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
	<title>List</title>
	<style>
            #top-right {
                position: absolute;
                top: 10px;
                right: 10px;
                padding: 10px;
                border-radius: 5px;
            }
            
            table {
                margin-top: 50px;
                width: 100%;
            }
            
            td, th {
                padding: 10px;
                text-align: center;
                border: 1px solid black;
            }
            
            tr:nth-child(odd) {
                background-color: #D3D3D3;
            }
        </style>
    </head>
    <body>
        <a id="top-right" href="http://localhost:9999/SE1722_PRJ301_Assignment/logout" style="color: #123456; font-size: 30px">LOGOUT</a><br/>
        <table border="1px"> 
            <tr style="background-color: #F27125">
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
                    <c:if test="${student.isGender()}"><input type="checkbox" checked="true">Male</c:if>
                    <c:if test="${!student.isGender()}"><input type="checkbox">Female</c:if>
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
    </body>
</html>

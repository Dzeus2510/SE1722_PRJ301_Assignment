
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List</title>
    </head>
    <body>
        <table border="1px"> 
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Dob</td>
                <td>Gmail</td>
                <td>Phone Number</td>
                <td>Group</td>
            </tr>
            <c:forEach items="${requestScope.students}" var="s" varStatus="loop">
                <tr 
                    <c:if test="${loop.index mod 2 ne 0}">style="background-color: gray;"</c:if>
                    
                    >
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>
                               <c:if test="${s.gender}">Male</c:if>
                               <c:if test="${!s.gender}">Female</c:if>
                               </td>
                    <td>
                <fmt:formatDate type = "date" 
         value = "${s.dob}" />
                    </td>
                    <td>${s.gmail}</td>
                    <td>${s.phone}</td>
                    <td>${s.group.name}</td>
                </tr>
                
            </c:forEach>
        </table>
    </body>
</html>

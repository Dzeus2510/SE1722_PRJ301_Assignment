
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="group" method="GET">
            <c:forEach items="${requestScope.groups}" var="g">
                <input type="checkbox"
                       <c:forEach items="${requestScope.groupIDs}" var="groupID">
                           <c:if test="${g.id eq groupID}">   
                               checked="checked"
                           </c:if>
                       </c:forEach>
                       name="groupID" value="${g.id}"/> ${g.name} 
            </c:forEach>
            <br/>
            <input type="submit" value="Search"/>
        </form>
        <c:if test="${requestScope.students ne null}">
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
                        <td><input type="checkbox" 
                                   <c:if test="${s.gender}">checked="checked"</c:if>
                                       /></td>
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
</c:if>
</body>
</html>
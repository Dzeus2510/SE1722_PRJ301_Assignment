
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="add" method="POST">
            Name: <input type="text" name="name"/> <br/>
            Gender: <input type="radio" name="gender" checked="checked" value="male"/> Male 
            <input type="radio" name="gender" value="female"/> Female <br/>
            Dob: <input type="date" name="dob"/> <br/>
            Gmail:<input type="text" name="gmail"/><br/>
            Phone Number:<input type="number" name="phone"/><br/>
            Group: <select name="groupID">
                <c:forEach items="${requestScope.groups}" var="g">
                    <option value="${g.id}">${g.name}</option>
                </c:forEach>
            </select> <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>

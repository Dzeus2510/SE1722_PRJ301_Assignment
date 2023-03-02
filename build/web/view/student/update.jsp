
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update</title>
    </head>
    <body>
         <form action="update" method="POST">
             <input type="hidden" name="sid" value="${param.id}"/>
             Name: <input type="text" value="${requestScope.s.name}" name="name"/> <br/>
            Gender: <input type="radio" name="gender" 
                           <c:if test="${requestScope.s.gender}">
                           checked="checked" 
                           </c:if>
                           value="male"/> Male 
            <input type="radio" name="gender"
                           <c:if test="${!requestScope.s.gender}">
                           checked="checked" 
                           </c:if>
                   value="female"/> Female <br/>
            Dob: <input type="date" name="dob" value="${requestScope.s.dob}"/> <br/>
            Gmail: <input type="text" value="${requestScope.s.gmail}" name="gmail"/> <br/>
            Phone: <input type="number" value="${requestScope.s.phone}" name="phone"/> <br/>
            Username:<input type="text" value="${requestScope.s.user.username eq u.username}" name="username"/> <br/>
            Group: <select name="groupID">
                <c:forEach items="${requestScope.groups}" var="g">
                    <option 
                        <c:if test="${requestScope.s.group.id eq g.id}">
                        selected="selected" 
                        </c:if>    
                            value="${g.id}">${g.name}</option>
                </c:forEach>
            </select> <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>

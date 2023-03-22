

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>

    </head>
    <body>
        <div class="title"><h1 style="font-family: Helvetica,Arial,sans-serif;">FPT University Academic Portal</h1></div>
        <div class="title-detail"><h1 style="font-family: Helvetica,Arial,sans-serif;">Timetable</h1></div>
        <div class="timetable">

            <table border="1px"> 
            <tr style="background-color: #F27125; color: black">
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d}<br/><fmt:formatDate value="${d}" pattern="EEEE"/>
            </td>
        </c:forEach>

    </tr>
    <c:forEach items="${requestScope.slots}" var="slot"> 
        <tr>
            <td style="background-color: #F27125; color: black">${slot.time}</td>
            <c:forEach items="${requestScope.dates}" var="d">
                <td>
                    <c:forEach items="${requestScope.s.groups}" var="g">
                        <c:forEach items="${g.sessions}" var="ses">
                            <c:if test="${ses.date eq d and ses.slot.id eq slot.id}">
                                ${g.name}(${g.course.name}) <br/>
                                ${ses.instructor.name}-${ses.room.name} <br/>
                                
                                    <c:choose>
    <c:when test="${ses.status}">
        <u style="color: green">attended</u> 
        <br />
    </c:when>    
    <c:otherwise>
        <u style="color: red">absent</u> 
        <br />
    </c:otherwise>
</c:choose>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>
        </div>

    </body>
</html>

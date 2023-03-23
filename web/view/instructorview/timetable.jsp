

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
        <style>
            body {
            background-color: white;
            color: #333333;
            font-family: Arial, sans-serif;
        }
        h2{
            color: #F27125;
            margin: 0;
            padding: 0;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        td {
            padding: 10px;
            text-align: center;
            border: 1px solid black;
        }
        td.time {
            background-color: #F27125;
            color: white;
            font-weight: bold;
        }
            #top-right {
			position: absolute;
			top: 0;
			right: 0;
			padding: 10px;
		}
	</style>
    </head>
        <a id="top-right" href="http://localhost:9999/SE1722_PRJ301_Assignment/logout" style="color: #123456; font-size: 30px">LOGOUT</a>
        <h2>
            <p>View Activities for ${sessionScope.user.displayname} </p>
        </h2>
        
            <div style="color: #123456">
                <p>Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...</p>
                <p>Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..</p>
                <p>Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...</p>
                <p>Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.</p>
                <p>Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..</p>
                <p>Little UK (LUK) thuộc tầng 5 tòa nhà Delta</p>
            </div>
        

        <table> 
            <tr>
                <td class="time"></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td class="time">${d}<br/><fmt:formatDate value="${d}" pattern="EEEE"/>
                    </td>
                </c:forEach>

            </tr>
            <c:forEach items="${requestScope.slots}" var="slot"> 
                <tr>
                    <td style="background-color: #F27125; color: black">${slot.time}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.instructor.groups}" var="g">
                                <c:forEach items="${g.sessions}" var="ses">
                                    <c:if test="${ses.date eq d and ses.slot.id eq slot.id}">
                                        ${g.course.name} at 
                                        ${ses.room.name} <br/>
                                        <a href="att?id=${ses.id}">Take Attend</a>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
        
    </body>
</html>

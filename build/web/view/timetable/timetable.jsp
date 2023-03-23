<%-- 
    Document   : timetable
    Created on : Mar 9, 2023, 8:29:04 AM
    Author     : sonnt
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timetable</title>
        	<style>
		table {
			font-size: 16px;
			border-collapse: collapse;
			width: 100%;
			max-width: 1200px;
			table-layout: fixed;
			padding: 10px;
		}
		th, td {
			padding: 10px;
			border: 1px solid black;
			text-align: center;
			vertical-align: middle;
			word-wrap: break-word;
		}
		th {
			background-color: #F27125;
			color: white;
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
        <h1 style="font-size: 24px; color: #123456">
        Activities for ${sessionScope.user.displayname} <br/>
        Note: These activities do not include extra-curriculum activities, such as club activities ... <br/>
        Chú thích: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa, ví dụ như hoạt động câu lạc bộ ...<br/>
        Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...<br/>
        Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..<br/>
        Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...<br/>
        Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.<br/>
        Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..<br/>
        Little UK (LUK) thuộc tầng 5 tòa nhà Delta<br/>
        </h1>
        <table border="1px"> 
            <tr style="background-color: #F27125; color: black">
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d}<br/><fmt:formatDate value="${d}" pattern="EEEE"/>
            </td>
        </c:forEach>
            
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
</body>
</html>

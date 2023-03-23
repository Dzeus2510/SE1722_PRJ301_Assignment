

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance Report</title>
        <style>
            body {
                background-color: #FFFFFF;
                color: #F27125;
                font-family: Arial, sans-serif;
            }
            
            .title {
                text-align: center;
                margin-top: 50px;
                margin-bottom: 30px;
            }
            
            .title h1 {
                font-size: 36px;
                font-weight: bold;
            }
            
            .title-detail {
                text-align: center;
                margin-bottom: 50px;
            }
            
            .title-detail h2 {
                font-size: 24px;
                font-weight: bold;
            }
            
            table {
                margin: 0 auto;
                border-collapse: collapse;
                width: 100%;
            }
            
            th, td {
                padding: 10px;
                text-align: left;
                border: 1px solid black;
            }
            
            th {
                font-weight: bold;
                border-bottom: 2px solid #F27125;
            }
            
            tr:nth-child(even) {
                background-color: #FCE8D6;
            }
            
            tr:hover {
                background-color: #FFE4C4;
            }
            
            td span.present {
                color: green;
            }
            
            td span.absent {
                color: red;
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
        <div class="title-detail"><h2>View attendance for ${sessionScope.user.displayname}<br/> 
                
            </h2></div>        
        

        <c:if test="${requestScope.attendance ne null}">

            <div class="timetable">
                <c:set var="p" value="0"/>
                <c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
                </c:forEach>
               
                <table>
                    <tr>
                    <td>No.</td>
                    <td>Date</td>   
                    <td>Time Slot</td>
                    <td>Room</td>
                    <td>Instructor</td>
                    <td>Group</td>
                    <td>Status</td>
                    <td>Instructor's Comment</td>
                    </tr>
                    <tbody>
                        <c:set var="t" value="0"/>
                        <c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
                            <tr>
                                <td style="width: 40px">${loop.index+1}</td>
                                <td style="width: 300px"><fmt:formatDate value="${a.session.date}" pattern="EEEE dd/MMMM/yyyy" /></td>
                                <td style="width: 150px"><b>${a.session.slot.id}</b>.${a.session.slot.time}</td>
                                <td style="width: 100px">${a.session.room.name}</td>
                                <td style="width: 150px">${a.session.instructor.name}</td>
                                <td style="width: 150px">${a.session.group.name}</td>

                                <td style="width: 150px">
                                    <span ${a.status eq  false ? 'style="color: red"': a.status eq  true ? 'style="color: green"': 'style="color: black"'}> ${a.status eq null ? 'how' : a.status eq true ? 'present' : 'absent/not yet'}</span>
                                </td>
                                <td>${a.comment}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </body>
</html>

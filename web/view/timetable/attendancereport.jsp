

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance Report</title>

    </head>
    <body>
        <div class="title"><h1>FPT University Academic Portal</h1></div>        
        <div class="title-detail"><h2>View attendance for ${sessionScope.user.displayname}</h2></div>        
<!--        <div class="formm">
            <form action="viewattendstudent" method="GET">
                <input type="hidden" name="studenId" value="${sessionScope.user.studentId}">
                <h3>Choose the course:</h3> 
                <select style="height: 30px" name="courseId">

                        <option value="${c.course.courseId}"  ${course == c.course.courseId ? 'selected':''}   >${c.groupName}(${c.course.name})</option>

                </select> 
                <input class="button-66" type="submit" value="View" />
            </form>
        </div>-->

        <c:if test="${requestScope.attendance ne null}">

            <div class="timetable">
                <c:set var="p" value="0"/>
                <c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
                    <c:if test="${a.status eq 'absent'}">
                        <c:set var="p" value="${p+1}"/>
                    </c:if>
                </c:forEach>
                <c:set var="size" value="${requestScope.attendance.size()}"/>
                <fmt:formatNumber var="aa" value="${p/size*100}" pattern="##"/>
                <p class="percentage">ABSENT: ${aa}% ABSENT SO FAR ( ${p} ABSENT ON ${size} TOTAL).</p>
                <table>
                    <thead>
                    <th>NO</th>
                    <th>DATE</th>   
                    <th>SLOT</th>
                    <th>ROOM</th>
                    <th>LECTURER</th>
                    <th>GROUP NAME</th>
                    <th>ATTEDANCE STATUS</th>
                    <th>LECTURER'S COMMENT</th>
                    </thead>
                    <tbody>
                        <c:set var="t" value="0"/>
                        <c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
                            <tr>
                                <td style="width: 50px">${loop.index+1}</td>
                                <td style="width: 600px"><fmt:formatDate value="${a.session.date}" pattern="EEEE dd/MMMM/yyyy" /></td>
                                <td style="width: 500px">${a.session.slot.id}_${a.session.slot.time}</td>
                                <td>${a.session.room.name}</td>
                                <td>${a.session.instructor.name}</td>
                                <td>${a.session.group.groupName}</td>

                                <td>
                                    <c:set var="t" value="${a.status}"/>
                                    <span ${t eq  "absent" ? 'style="color: red"': t eq  "attended" ? 'style="color: green"': 'style="color: black"'}> ${a.status eq null ? 'Future': a.status eq "attended" ? 'present' : a.status}</span>
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

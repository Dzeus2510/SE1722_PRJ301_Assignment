<%-- 
    Document   : attendance
    Created on : Mar 17, 2023, 11:39:57 AM
    Author     : Doan Ngoc Vu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance</title>
        <style>
            #top-right {
			position: absolute;
			top: 0;
			right: 0;
			padding: 10px;
		}
		body {
			background-color: #fff;
			font-family: Arial, sans-serif;
			color: #000;
		}
		
		h1 {
			color: #F27125;
			text-align: center;
		}
		
		table {
			border-collapse: collapse;
			margin-top: 50px;
		}
		
		table, th, td {
			border: 1px solid #F27125;
		}
		
		th {
			background-color: #F27125;
			color: #fff;
			padding: 10px;
			text-align: center;
		}
		
		td {
			padding: 5px;
			text-align: center;
		}
		
		input[type="text"] {
			width: 100%;
			box-sizing: border-box;
			padding: 5px;
		}
		
		input[type="submit"] {
			background-color: #F27125;
			color: #fff;
			border: none;
			padding: 10px;
			cursor: pointer;
			margin-top: 20px;
			margin-bottom: 50px;
			font-size: 16px;
			font-weight: bold;
		}
		
		input[type="submit"]:hover {
			background-color: #fff;
			color: #F27125;
			border: 2px solid #F27125;
		}
	
        </style>
    </head>
    <body>
        <a id="top-right" href="http://localhost:9999/SE1722_PRJ301_Assignment/logout" style="color: #123456; font-size: 30px">LOGOUT</a>
        <form action="att" method="POST"> 
            <table border="1px">
                <tr>
                    <td>Seq</td>
                    <td>Student Id</td>
                    <td>Name</td>
                    <td>Present/Absent</td>
                    <td>Description</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                    <tr>

                        <td>${loop.index +1}</td>
                        <td>${a.student.id}</td>
                        <td>${a.student.name}</td>
                        <td>
                            <input type="radio" 
                                   <c:if test="${!a.status}">
                                   checked="checked" 
                                   </c:if>
                                   name="status${a.student.id}" value="absent"/> Absent
                            <input type="radio" 
                                   <c:if test="${a.status}">
                                   checked="checked" 
                                   </c:if>
                                   name="status${a.student.id}" value="present" /> Present
                        </td>
                        <td>
                            <input type="hidden" name="sid" value="${a.student.id}"/>
                            <input type="hidden" name="aid${a.student.id}" value="${a.id}"/>
                            <input type="text" name="description${a.student.id}" value="${a.comment}"/></td>
                    </tr>    

                </c:forEach>
            </table>
            <input type="hidden" name="sessionid" value="${param.id}"/>
            <input type="submit" value="Save"/>
        </form>
        
    </body>
</html>

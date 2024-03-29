/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.attendance;

import controller.authentication.BaseRequiredAuthenticatedControllerForInstructor;
import dal.AttendanceDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Attendance;
import model.Group;
import model.Instructor;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.User;

/**
 *
 * @author Doan Ngoc Vu
 */
public class TakeAttendanceController extends BaseRequiredAuthenticatedControllerForInstructor {
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        int sessionid = Integer.parseInt(request.getParameter("id"));
        AttendanceDBContext db = new AttendanceDBContext();
        ArrayList<Attendance> atts = db.getAttendancesBySession(sessionid);
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/instructorview/att.jsp").forward(request, response);
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String[] sids = request.getParameterValues("sid");
        int sessionid = Integer.parseInt(request.getParameter("sessionid"));
        Session ss = new Session();
        ss.setId(sessionid);
        
        ArrayList<Attendance> atts = new ArrayList<>();
        for (String sid : sids) {
            boolean status = request.getParameter("status"+sid).equals("present");
            int aid = Integer.parseInt(request.getParameter("aid"+sid));
            String description = request.getParameter("description"+sid);
            Attendance a = new Attendance();
            Student s = new Student();
            s.setId(Integer.parseInt(sid));
            a.setId(aid);
            a.setStatus(status);
            a.setComment(description);
            a.setStudent(s);
            a.setSession(ss);
            atts.add(a);
        }
        AttendanceDBContext db = new AttendanceDBContext();
        db.update(atts, sessionid);
        response.sendRedirect("att?id="+sessionid);
    }
}


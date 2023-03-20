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
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        int sessionid = Integer.parseInt(request.getParameter("id"));
        AttendanceDBContext db = new AttendanceDBContext();
        ArrayList<Attendance> atts = db.getAttendancesBySession(sessionid);
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/timetable/att.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String[] sids = request.getParameterValues("sid");
        int sessionid = Integer.parseInt(request.getParameter("sess"));
        ArrayList<Attendance> atts = new ArrayList<>();
        for (String sid : sids) {
            boolean status = request.getParameter("status"+sid).equals("present");
            int aid = Integer.parseInt(request.getParameter("aid"+sid));
            String description = request.getParameter("description"+sid);
            Attendance a = new Attendance();
            Student s = new Student();
            s.setId(Integer.parseInt(sid));
            a.setStudent(s);
            TimeSlot slot = new TimeSlot();
            slot.setId(Integer.parseInt(request.getParameter("slot")));
            Group g = new Group();
            g.setId(Integer.parseInt(request.getParameter("groupid")));
            Session ss = new Session();
            
            ss.setGroup(g);
            ss.setSlot(slot);
            a.setSession(ss);
            a.setId(aid);
            a.setStatus(status);
            a.setComment(description);
            atts.add(a);
        }
        AttendanceDBContext t = new AttendanceDBContext();
        t.update(atts, sessionid);
        response.sendRedirect("listattendancegroups");
    }

}


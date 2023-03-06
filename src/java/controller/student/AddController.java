/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.GroupDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Group;
import model.Student;
import model.User;

public class AddController extends BaseRequiredAuthenticatedController {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
            GroupDBContext db = new GroupDBContext();
            ArrayList<Group> groups = db.all();
            request.setAttribute("groups", groups);
            request.getRequestDispatcher("../view/student/add.jsp").forward(request, response);
      
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
            String raw_name = request.getParameter("name");
            String raw_gender = request.getParameter("gender");
            String raw_dob = request.getParameter("dob");
            String raw_gmail = request.getParameter("gmail");
            String raw_phone = request.getParameter("phone");
            String raw_groupID = request.getParameter("groupID");
            

            // validate input data
            Student s = new Student();
            s.setName(raw_name);
            s.setGender(raw_gender.equals("male"));
            s.setDob(Date.valueOf(raw_dob));
            s.setGmail(raw_gmail);
            s.setPhone(raw_phone);
            Group g = new Group();
            g.setId(Integer.parseInt(raw_groupID));
            s.setGroup(g);

            StudentDBContext db = new StudentDBContext();
            db.insert(s);

            //response.getWriter().println("inserted succesful!");
            response.sendRedirect("list");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

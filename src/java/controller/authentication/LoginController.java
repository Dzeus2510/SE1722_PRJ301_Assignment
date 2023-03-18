/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.authentication;

import dal.UserDBContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author sonnt
 */
public class LoginController extends HttpServlet {
   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("view/authentication/login.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Boolean role = Boolean.parseBoolean(request.getParameter("role"));
        UserDBContext db = new UserDBContext();
        User user = db.get(username, password, role);
        
        if(user != null){
        {   if(user.isRole()==true){
            request.getSession().setAttribute("user", user);
            response.getWriter().println("login successful!");
            response.sendRedirect(request.getContextPath() + "/student/list");
        } else if(user.isRole()==false){
            request.getSession().setAttribute("user", user);
            response.getWriter().println("login successful!");
            response.sendRedirect(request.getContextPath() + "/timetable/timetable?sid=4&from=2023-03-14&to=2023-03-22");
        }
        }
        }
        if(user == null)
        {
//            request.setAttribute("error", "WRONG USERNAME OR PASSWORD");
//            RequestDispatcher rd = request.getRequestDispatcher("/login");
//            rd.include(request, response);
            response.sendRedirect("http://localhost:9999/SE1722_PRJ301_Assignment/loginfailed");
        }
           
    }
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

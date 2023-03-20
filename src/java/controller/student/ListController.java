/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;


import controller.authentication.BaseRequiredAuthenticatedControllerForInstructor;
import dal.DBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Student;
import model.User;

public class ListController extends BaseRequiredAuthenticatedControllerForInstructor {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBContext<Student> db = new StudentDBContext();
        ArrayList<Student> students = db.all();
        req.setAttribute("students", students);
        req.getRequestDispatcher("../view/student/list.jsp").forward(req, resp);
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);
    }
    
}

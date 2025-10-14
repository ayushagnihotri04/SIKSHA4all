package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.time.LocalDate;
import javax.servlet.http.HttpSession;

import models.Teacher;

@WebServlet("/profile.do")
public class ProfileServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession();
        String name = request.getParameter("teacher-name");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String department = request.getParameter("department");
        String dobString = request.getParameter("dob");

        String nextPage = "teachers.html";
        LocalDate localDate = LocalDate.parse(dobString); // java.time.LocalDate
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
       
        Teacher teacher = new Teacher(name,email,contact,department,sqlDate);
        if(teacher.updateUser()){
            session.setAttribute("profile",teacher);
            nextPage = "teacher-portal.jsp";
        }
        
        response.sendRedirect(nextPage);


    }
}
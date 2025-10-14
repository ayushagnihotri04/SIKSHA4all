package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Teacher;
import models.Student;

@WebServlet("/signin.do")
public class SigninServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String nextPage = "index.html";

        String email = request.getParameter("signin_email");
        String password = request.getParameter("signin_password");
        String utype = request.getParameter("utype");
        System.out.println(utype);

        switch (utype) {
            case "teacher":
                Teacher teacher = new Teacher(email,password);
                if(teacher.signinUser()){
                    session.setAttribute("teacher",teacher);
                    nextPage = "teachers.html";
                }
                break;
            case "student":
                Student student = new Student(email,password);
                if(student.signinUser()){
                    session.setAttribute("student",student);
                    nextPage = "institution.html";
                }
                break;
            
            default:
                break;
        }
 
        response.sendRedirect(nextPage);
    }
}
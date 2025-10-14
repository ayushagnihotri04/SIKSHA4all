package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import models.Teacher;
import models.Student;


@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        String userTypeId = request.getParameter("usertype");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nextPage = "index.html";
        
        switch (userTypeId) {
            case "teacher":
                Teacher teacher = new Teacher(name,email,password);
                if(teacher.saveUser()){
                    nextPage = "teachers.html";
                }
                break;
            case "student":
                Student student = new Student(name,email,password);
                if(student.saveUser()){
                    nextPage = "institution.html";
                }
                break;
            
            default:
                break;
        }
        response.sendRedirect(nextPage);


    }
}
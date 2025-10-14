package controllers;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/markAttendance")
public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("studentName");
        String roll = request.getParameter("enrollmentNumber");
        String institution = request.getParameter("institution");
        String className = request.getParameter("class");
        String subject = request.getParameter("subject");
        String lectureNumber = request.getParameter("lectureNumber");
        String status = request.getParameter("status");
        String date = request.getParameter("date");

        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/education", "root", "12345");
            

            String sql = "INSERT INTO attendance (student_name, enrollment_number, institution, class, subject, lecture_number, status, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, roll);
            stmt.setString(3, institution);
            stmt.setString(4, className);
            stmt.setString(5, subject);
            stmt.setString(6, lectureNumber);
            stmt.setString(7, status);
            stmt.setString(8, date);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                out.println("✅ Attendance marked successfully.");
            } else {
                out.println("❌ Failed to mark attendance.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("❌ Error: " + e.getMessage());
        }
    }
}

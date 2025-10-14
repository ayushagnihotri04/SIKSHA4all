package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Student {
    private Integer studentId;
    private String name;
    private String email;
    private String password;

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Student(String email, String password){
        this.email = email;
        this.password = password;
    }

    static StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

    public boolean signinUser(){
        boolean flag = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/education?user=root&password=12345");
            String query = "select * from students where email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if(spe.checkPassword(password, rs.getString("password"))) {
                    name = rs.getString("name");                    
                    studentId = rs.getInt("student_id");
                       
                    flag = true;
                }
            }
            
            con.close(); 
        } catch(SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean saveUser() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/education?user=root&password=12345");

            
                String query = "insert into students (name,email,password) value (?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, spe.encryptPassword(password));
                
                int res = ps.executeUpdate();

                if(res == 1) 
                    flag = true;
            
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

}

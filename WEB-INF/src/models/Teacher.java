package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class Teacher{
    private Integer teacherId;
    private String name;
    private String email;
    private String password;
    private String contact;
    private String department;
    private java.sql.Date dob;

    public Teacher(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Teacher(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Teacher(String name, String email, String contact, String department, java.sql.Date dob){
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.department = department;
        this.dob = dob;
    }

    static StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

    public boolean updateUser(){
        boolean flag = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/education?user=root&password=12345");
            String query = "UPDATE teachers SET name=?, contact=?, department=?, dob=? WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            // ps.setString(2, password);
            ps.setString(2, contact);
            ps.setString(3, department);
            ps.setDate(4, dob);
            ps.setString(5, email);
            int result = ps.executeUpdate();
            if(result > 0){
                flag = true;
            }
            
            con.close(); 
        } catch(SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

        return flag;

    }

    public boolean signinUser(){
        boolean flag = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/education?user=root&password=12345");
            String query = "select * from teachers where email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if(spe.checkPassword(password, rs.getString("password"))) {
                    name = rs.getString("name");                    
                    teacherId = rs.getInt("teacher_id");
                    dob = rs.getDate("dob"); 
                    contact = rs.getString("contact");
                    department = rs.getString("department");   
                    flag = true;
                }
            }
            System.out.println(flag);
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

            
                String query = "insert into teachers (name,email,password) value (?,?,?)";
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

    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(java.sql.Date dob) {
        this.dob = dob;
    }
    
}
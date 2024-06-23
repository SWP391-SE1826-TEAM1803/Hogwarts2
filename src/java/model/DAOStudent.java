package model;

import entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStudent extends DBConnect {

    public int insertStudent(Student student) {
        int n = 0;
        String sql = "INSERT INTO Student (FullName, DoB, Gender, Address, UserID) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, student.getFullName());
            pre.setDate(2, student.getDoB());
            pre.setString(3, student.getGender());
            pre.setString(4, student.getAddress());
            pre.setInt(5, student.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStudent(Student student) {
        int n = 0;
        String sql = "UPDATE Student SET FullName = ?, DoB = ?, Gender = ?, Address = ?, UserID = ? WHERE StudentID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, student.getFullName());
            pre.setDate(2, student.getDoB());
            pre.setString(3, student.getGender());
            pre.setString(4, student.getAddress());
            pre.setInt(5, student.getUserID());
            pre.setInt(6, student.getStudentID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteStudent(int studentID) {
        int n = 0;
        String sql = "DELETE FROM Student WHERE StudentID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, studentID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Student> getAllStudents() {
        Vector<Student> vector = new Vector<>();
        String sql = "SELECT * FROM Student";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                String fullName = rs.getString("FullName");
                java.sql.Date dob = rs.getDate("DoB");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                int userID = rs.getInt("UserID");
                Student student = new Student(studentID, fullName, dob, gender, address, userID);
                vector.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Student getStudentByID(int studentID) {
        Student student = null;
        String sql = "SELECT * FROM Student WHERE StudentID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, studentID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("FullName");
                java.sql.Date dob = rs.getDate("DoB");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                int userID = rs.getInt("UserID");
                student = new Student(studentID, fullName, dob, gender, address, userID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }
}

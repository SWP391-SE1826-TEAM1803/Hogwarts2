/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Student;
import entity.Student1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DAOStudent1 extends DBConnect {
    public Vector<Student1> getAllStudents(String sql) {
        Vector<Student1> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                String fullName = rs.getString("FullName");
                String dob = rs.getString("DoB");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                int userID = rs.getInt("UserID");
                Student1 student = new Student1(studentID, fullName, dob, gender, address, userID);
                vector.add(student);
            }
            rs.close();
            state.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Student1> getAllStudentsWithoutClass(String schoolYearId) {
        Vector<Student1> students = new Vector<>();
        String query = "SELECT s.* FROM Student s " +
                       "LEFT JOIN Student_SchoolYear_Class ssc ON s.StudentID = ssc.StudentID " +
                       "LEFT JOIN SchoolYear_Class syc ON ssc.SyC_ID = syc.SyC_ID " +
                       "WHERE syc.SyID IS NULL OR syc.SyID != ?";
        try  {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                Student1 student = new Student1();
                student.setStudentID(rs.getInt("StudentID"));
                student.setFullName(rs.getString("FullName"));
                student.setDoB(rs.getString("DoB"));
                student.setGender(rs.getString("Gender"));
                student.setAddress(rs.getString("Address"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}

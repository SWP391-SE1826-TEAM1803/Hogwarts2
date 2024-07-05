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
}

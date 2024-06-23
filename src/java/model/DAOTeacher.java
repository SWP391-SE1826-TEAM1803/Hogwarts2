package model;

import entity.Teacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTeacher extends DBConnect {

    public int insertTeacher(Teacher teacher) {
        int n = 0;
        String sql = "INSERT INTO Teacher (UserID, Degree) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacher.getUserID());
            pre.setString(2, teacher.getDegree());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateTeacher(Teacher teacher) {
        int n = 0;
        String sql = "UPDATE Teacher SET UserID = ?, Degree = ? WHERE TeacherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacher.getUserID());
            pre.setString(2, teacher.getDegree());
            pre.setInt(3, teacher.getTeacherID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteTeacher(int teacherID) {
        int n = 0;
        String sql = "DELETE FROM Teacher WHERE TeacherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Teacher> getAllTeachers() {
        Vector<Teacher> vector = new Vector<>();
        String sql = "SELECT * FROM Teacher";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int teacherID = rs.getInt("TeacherID");
                int userID = rs.getInt("UserID");
                String degree = rs.getString("Degree");
                Teacher teacher = new Teacher(teacherID, userID, degree);
                vector.add(teacher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Teacher getTeacherByID(int teacherID) {
        Teacher teacher = null;
        String sql = "SELECT * FROM Teacher WHERE TeacherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("UserID");
                String degree = rs.getString("Degree");
                teacher = new Teacher(teacherID, userID, degree);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teacher;
    }
}

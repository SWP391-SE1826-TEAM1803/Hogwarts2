package model;

import entity.StudentSchoolYearClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStudentSchoolYearClass extends DBConnect {

    public int insertStudentSchoolYearClass(StudentSchoolYearClass studentSchoolYearClass) {
        int n = 0;
        String sql = "INSERT INTO StudentSchoolYearClass (StudentID, SyC_ID) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, studentSchoolYearClass.getStudentID());
            pre.setInt(2, studentSchoolYearClass.getSyC_ID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteStudentSchoolYearClass(int studentID, int syC_ID) {
        int n = 0;
        String sql = "DELETE FROM StudentSchoolYearClass WHERE StudentID = ? AND SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, studentID);
            pre.setInt(2, syC_ID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<StudentSchoolYearClass> getStudentSchoolYearClassesByStudentID(int studentID) {
        Vector<StudentSchoolYearClass> vector = new Vector<>();
        String sql = "SELECT * FROM StudentSchoolYearClass WHERE StudentID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, studentID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int syC_ID = rs.getInt("SyC_ID");
                StudentSchoolYearClass studentSchoolYearClass = new StudentSchoolYearClass(studentID, syC_ID);
                vector.add(studentSchoolYearClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<StudentSchoolYearClass> getStudentSchoolYearClassesBySyC_ID(int syC_ID) {
        Vector<StudentSchoolYearClass> vector = new Vector<>();
        String sql = "SELECT * FROM StudentSchoolYearClass WHERE SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syC_ID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                StudentSchoolYearClass studentSchoolYearClass = new StudentSchoolYearClass(studentID, syC_ID);
                vector.add(studentSchoolYearClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int getStudentCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Student_SchoolYear_Class";
        try (
             PreparedStatement pre = conn.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
}

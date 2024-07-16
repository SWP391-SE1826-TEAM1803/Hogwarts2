package model;

import entity.StudentSchoolYearClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStudentSchoolYearClass extends DBConnect {

    public int insertStudentSchoolYearClass(StudentSchoolYearClass studentSchoolYearClass) {
        int n = 0;
        String sql = "INSERT INTO Student_SchoolYear_Class (StudentID, SyC_ID) VALUES (?, ?)";
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
        String sql = "DELETE FROM Student_SchoolYear_Class WHERE StudentID = ? AND SyC_ID = ?";
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
        String sql = "SELECT * FROM Student_SchoolYear_Class WHERE StudentID = ?";
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
        String sql = "SELECT * FROM Student_SchoolYear_Class WHERE SyC_ID = ?";
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
    
     public Vector<StudentSchoolYearClass> getAllStudentSchoolYearClasses(String sql) {
        Vector<StudentSchoolYearClass> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                int syC_ID = rs.getInt("SyC_ID");
                StudentSchoolYearClass ssClass = new StudentSchoolYearClass(studentID, syC_ID);
                vector.add(ssClass);
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
    
   
    public void addStudentToClass(int studentId, int SyC_ID) {
        String sql = "INSERT INTO Student_SchoolYear_Class (StudentID, SyC_ID) VALUES (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, SyC_ID); // Adjust if you have a different way to determine SyC_ID
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

package model;

import entity.SchoolYearClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSchoolYearClass extends DBConnect {

    public int insertSchoolYearClass(SchoolYearClass schoolYearClass) {
        int n = 0;
        String sql = "INSERT INTO SchoolYear_Class (SyID, ClassID, CurID) VALUES (?, ?, ?)";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, schoolYearClass.getSyID());
            pre.setInt(2, schoolYearClass.getClassID());
            pre.setInt(3, schoolYearClass.getCurID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateSchoolYearClass(SchoolYearClass schoolYearClass) {
        int n = 0;
        String sql = "UPDATE SchoolYear_Class SET CurID = ? WHERE SyC_ID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, schoolYearClass.getCurID());
            pre.setInt(2, schoolYearClass.getSyC_ID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteSchoolYearClass(int syC_ID) {
        int n = 0;
        String deleteTeacherSQL = "DELETE FROM Teacher_SchoolYear_Class WHERE SyC_ID = ?";
        String deleteStudentSQL = "DELETE FROM Student_SchoolYear_Class WHERE SyC_ID = ?";
        String deleteSchoolYearClassSQL = "DELETE FROM SchoolYear_Class WHERE SyC_ID = ?";
        String deleteSchedulesSQL = "DELETE FROM Schedules WHERE SyC_ID=?";
        try {
            // Start transaction
            conn.setAutoCommit(false);

            // Delete from Teacher_SchoolYear_Class
            try (PreparedStatement pre = conn.prepareStatement(deleteTeacherSQL)) {
                pre.setInt(1, syC_ID);
                pre.executeUpdate();
            }

            // Delete from Student_SchoolYear_Class
            try (PreparedStatement pre = conn.prepareStatement(deleteStudentSQL)) {
                pre.setInt(1, syC_ID);
                pre.executeUpdate();
            }

            // Delete from SchoolYear_Class
            try (PreparedStatement pre = conn.prepareStatement(deleteSchoolYearClassSQL)) {
                pre.setInt(1, syC_ID);
                n = pre.executeUpdate();
            }

            // Delete from Schedules
            try (PreparedStatement pre = conn.prepareStatement(deleteSchedulesSQL)) {
                pre.setInt(1, syC_ID);
                pre.executeUpdate();
            }

            // Commit transaction
            conn.commit();
        } catch (SQLException ex) {
            try {
                // Rollback transaction in case of error
                conn.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // Set auto-commit back to true
                conn.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, autoCommitEx);
            }
        }
        return n;
    }

    public Vector<SchoolYearClass> getAllSchoolYearClasses(String sql) {
        Vector<SchoolYearClass> vector = new Vector<>();
        try (PreparedStatement pre = conn.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                int syC_ID = rs.getInt("SyC_ID");
                int syID = rs.getInt("SyID");
                int classID = rs.getInt("ClassID");
                int curID = rs.getInt("CurID");
                SchoolYearClass schoolYearClass = new SchoolYearClass(syC_ID, syID, classID, curID);
                vector.add(schoolYearClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public SchoolYearClass getSchoolYearClassByID(int syC_ID) {
        SchoolYearClass schoolYearClass = null;
        String sql = "SELECT * FROM SchoolYear_Class WHERE SyC_ID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, syC_ID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int syID = rs.getInt("SyID");
                int classID = rs.getInt("ClassID");
                int curID = rs.getInt("CurID");
                schoolYearClass = new SchoolYearClass(syC_ID, syID, classID, curID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schoolYearClass;
    }

    public int getSycIDByTeacherID(int teacherID) {
        int syC_ID = -1;
        String sql = "SELECT syc.Syc_ID FROM SchoolYear_Class syc " +
                     "INNER JOIN Teacher_SchoolYear_Class tsc ON tsc.Syc_ID = syc.Syc_ID " +
                     "INNER JOIN Teacher t ON t.TeacherID = tsc.TeacherID " +
                     "WHERE tsc.TeacherID = ? " +
                     "AND syc.SyID = (SELECT MAX(SyID) FROM SchoolYear_Class)";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, teacherID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                syC_ID = rs.getInt("SyC_ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return syC_ID;
    }
    
    public SchoolYearClass getLastInsertedSchoolYearClass() {
        SchoolYearClass syClass = null;
        String sql = "SELECT * FROM SchoolYear_Class ORDER BY SyC_ID DESC LIMIT 1";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                int SyC_ID = rs.getInt("SyC_ID");
                int SyID = rs.getInt("SyID");
                int ClassID = rs.getInt("ClassID");
                int CurID = rs.getInt("CurID");
                syClass = new SchoolYearClass(SyC_ID, SyID, ClassID, CurID);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, e);
        }
        return syClass;
    }
}

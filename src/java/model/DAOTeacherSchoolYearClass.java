package model;

import entity.TeacherSchoolYearClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTeacherSchoolYearClass extends DBConnect {

    public int insertTeacherSchoolYearClass(TeacherSchoolYearClass teacherSchoolYearClass) {
        int n = 0;
        String sql = "INSERT INTO TeacherSchoolYearClass (TeacherID, SyC_ID) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherSchoolYearClass.getTeacherID());
            pre.setInt(2, teacherSchoolYearClass.getSyC_ID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteTeacherSchoolYearClass(int teacherID, int syC_ID) {
        int n = 0;
        String sql = "DELETE FROM TeacherSchoolYearClass WHERE TeacherID = ? AND SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            pre.setInt(2, syC_ID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<TeacherSchoolYearClass> getTeacherSchoolYearClassesByTeacherID(int teacherID) {
        Vector<TeacherSchoolYearClass> vector = new Vector<>();
        String sql = "SELECT * FROM TeacherSchoolYearClass WHERE TeacherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int syC_ID = rs.getInt("SyC_ID");
                TeacherSchoolYearClass teacherSchoolYearClass = new TeacherSchoolYearClass(teacherID, syC_ID);
                vector.add(teacherSchoolYearClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<TeacherSchoolYearClass> getTeacherSchoolYearClassesBySyC_ID(int syC_ID) {
        Vector<TeacherSchoolYearClass> vector = new Vector<>();
        String sql = "SELECT * FROM TeacherSchoolYearClass WHERE SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syC_ID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int teacherID = rs.getInt("TeacherID");
                TeacherSchoolYearClass teacherSchoolYearClass = new TeacherSchoolYearClass(teacherID, syC_ID);
                vector.add(teacherSchoolYearClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}

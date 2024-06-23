package model;

import entity.SchoolYear;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSchoolYear extends DBConnect {

    public int insertSchoolYear(SchoolYear schoolYear) {
        int n = 0;
        String sql = "INSERT INTO SchoolYear (SyName, DateStart, DateEnd) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, schoolYear.getSyName());
            pre.setDate(2, schoolYear.getDateStart());
            pre.setDate(3, schoolYear.getDateEnd());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateSchoolYear(SchoolYear schoolYear) {
        int n = 0;
        String sql = "UPDATE SchoolYear SET SyName = ?, DateStart = ?, DateEnd = ? WHERE SyID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, schoolYear.getSyName());
            pre.setDate(2, schoolYear.getDateStart());
            pre.setDate(3, schoolYear.getDateEnd());
            pre.setInt(4, schoolYear.getSyID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteSchoolYear(int syID) {
        int n = 0;
        String sql = "DELETE FROM SchoolYear WHERE SyID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<SchoolYear> getAllSchoolYears() {
        Vector<SchoolYear> vector = new Vector<>();
        String sql = "SELECT * FROM SchoolYear";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int syID = rs.getInt("SyID");
                String syName = rs.getString("SyName");
                java.sql.Date dateStart = rs.getDate("DateStart");
                java.sql.Date dateEnd = rs.getDate("DateEnd");
                SchoolYear sy = new SchoolYear(syID, syName, dateStart, dateEnd);
                vector.add(sy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public SchoolYear getSchoolYearByID(int syID) {
        SchoolYear schoolYear = null;
        String sql = "SELECT * FROM SchoolYear WHERE SyID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String syName = rs.getString("SyName");
                java.sql.Date dateStart = rs.getDate("DateStart");
                java.sql.Date dateEnd = rs.getDate("DateEnd");
                schoolYear = new SchoolYear(syID, syName, dateStart, dateEnd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schoolYear;
    }
}

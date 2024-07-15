package model;

import entity.CurriculumDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOCurriculumDate extends DBConnect {

    public int insertCurriculumDate(CurriculumDate curriculumDate) {
        int n = 0;
        String sql = "INSERT INTO CurriculumDate (DateNumber, CurID) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, curriculumDate.getDateNumber());
            pre.setInt(2, curriculumDate.getCurID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculumDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCurriculumDate(CurriculumDate curriculumDate) {
        int n = 0;
        String sql = "UPDATE CurriculumDate SET DateNumber = ?, CurID = ? WHERE CurDateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, curriculumDate.getDateNumber());
            pre.setInt(2, curriculumDate.getCurID());
            pre.setInt(3, curriculumDate.getCurDateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculumDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCurriculumDate(int curDateID) {
        int n = 0;
        String sql = "DELETE FROM CurriculumDate WHERE CurDateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, curDateID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculumDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<CurriculumDate> getAllCurriculumDates(String sql) {
        Vector<CurriculumDate> vector = new Vector<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int curDateID = rs.getInt("CurDateID");
                String dateNumber = rs.getString("DateNumber");
                int curID = rs.getInt("CurID");
                CurriculumDate curriculumDate = new CurriculumDate(curDateID, dateNumber, curID);
                vector.add(curriculumDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculumDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<CurriculumDate> getAllCurriculumDatesWithCurName(int teacherID) {
        Vector<CurriculumDate> vector = new Vector<>();
        String sql = "Select cd.*, c.CurName from CurriculumDate cd\n"
                + "	INNER JOIN Curriculum c ON c.CurID = cd.CurID\n"
                + "	INNER JOIN SchoolYear_Class syc ON syc.CurID = c.CurID\n"
                + "	INNER JOIN Teacher_SchoolYear_class tsc ON tsc.Syc_ID = syc.Syc_ID\n"
                + "	INNER JOIN SchoolYear sy ON sy.SyID = syc.SyID\n"
                + "	WHERE \n"
                + "    tsc.TeacherID = ?\n"
                + "	AND syc.SyID = (SELECT MAX(SyID) FROM SchoolYear_Class)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int curDateID = rs.getInt("CurDateID");
                String dateNumber = rs.getString("DateNumber");
                int curID = rs.getInt("CurID");
                String curName = rs.getString("CurName");
                CurriculumDate curriculumDate = new CurriculumDate(curDateID, dateNumber, curID, curName);
                vector.add(curriculumDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculumDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public CurriculumDate getCurriculumDateByID(int curDateID) {
        CurriculumDate curriculumDate = null;
        String sql = "SELECT * FROM CurriculumDate WHERE CurDateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, curDateID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String dateNumber = rs.getString("DateNumber");
                int curID = rs.getInt("CurID");
                curriculumDate = new CurriculumDate(curDateID, dateNumber, curID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculumDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curriculumDate;
    }

    public Vector<CurriculumDate> searchCurriculumDatesByDateNumber(int teacherID, String dateNumber) {
        Vector<CurriculumDate> vector = new Vector<>();
        String sql = "SELECT cd.CurDateID, cd.DateNumber, cd.CurID, c.CurName "
                + "FROM CurriculumDate cd "
                + "INNER JOIN Curriculum c ON cd.CurID = c.CurID "
                + "INNER JOIN SchoolYear_Class syc ON syc.CurID = cd.CurID "
                + "INNER JOIN Teacher_SchoolYear_class tsc ON tsc.Syc_ID = syc.Syc_ID "
                + "WHERE tsc.TeacherID = ? AND cd.DateNumber LIKE ? "
                + "AND syc.SyID = (SELECT MAX(SyID) FROM SchoolYear_Class)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            pre.setString(2, "%" + dateNumber + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                CurriculumDate cd = new CurriculumDate(rs.getInt("CurDateID"), rs.getString("DateNumber"), rs.getInt("CurID"), rs.getString("CurName"));
                vector.add(cd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculumDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

}

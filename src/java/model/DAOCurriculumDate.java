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
}

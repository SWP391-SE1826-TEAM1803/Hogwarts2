package model;

import entity.CurDateAct;
import entity.CurriculumDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOCurDateAct extends DBConnect {

    public int insertCurDateAct(CurDateAct curDateAct) {
        int n = 0;
        String sql = "INSERT INTO CurDateAct (Act, TimeStart, TimeEnd, CurDateID) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, curDateAct.getAct());
            pre.setString(2, curDateAct.getTimeStart());
            pre.setString(3, curDateAct.getTimeEnd());
            pre.setInt(4, curDateAct.getCurDateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurDateAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCurDateAct(CurDateAct curDateAct) {
        int n = 0;
        String sql = "UPDATE CurDateAct SET Act = ?, TimeStart = ?, TimeEnd = ?, CurDateID = ? WHERE CdtID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, curDateAct.getAct());
            pre.setString(2, curDateAct.getTimeStart());
            pre.setString(3, curDateAct.getTimeEnd());
            pre.setInt(4, curDateAct.getCurDateID());
            pre.setInt(5, curDateAct.getCdtID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurDateAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCurDateAct(int cdtID) {
        int n = 0;
        String sql = "DELETE FROM CurDateAct WHERE CdtID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cdtID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurDateAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<CurDateAct> getAllCurDateActs() {
        Vector<CurDateAct> vector = new Vector<>();
        String sql = "SELECT * FROM CurDateAct";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cdtID = rs.getInt("CdtID");
                String act = rs.getString("Act");
                String timeStart = rs.getString("TimeStart");
                String timeEnd = rs.getString("TimeEnd");
                int curDateID = rs.getInt("CurDateID");
                CurDateAct curDateAct = new CurDateAct(cdtID, act, timeStart, timeEnd, curDateID);
                vector.add(curDateAct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurDateAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<CurDateAct> getAllCurDateActs(String sql) {
        Vector<CurDateAct> vector = new Vector<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cdtID = rs.getInt("CdtID");
                String act = rs.getString("Act");
                String timeStart = rs.getString("TimeStart");
                String timeEnd = rs.getString("TimeEnd");
                int curDateID = rs.getInt("CurDateID");
                CurDateAct curDateAct = new CurDateAct(cdtID, act, timeStart, timeEnd, curDateID);
                vector.add(curDateAct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurDateAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public CurDateAct getCurDateActByID(int cdtID) {
        CurDateAct curDateAct = null;
        String sql = "SELECT * FROM CurDateAct WHERE CdtID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cdtID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String act = rs.getString("Act");
                String timeStart = rs.getString("TimeStart");
                String timeEnd = rs.getString("TimeEnd");
                int curDateID = rs.getInt("CurDateID");
                curDateAct = new CurDateAct(cdtID, act, timeStart, timeEnd, curDateID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurDateAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curDateAct;
    }

    public Vector<CurDateAct> searchCurDateActByDateNumber(int teacherID, String dateNumber) {
        Vector<CurDateAct> vector = new Vector<>();
        try {
            String sql = "Select cda.* from CurDateAct cda\n"
                    + " INNER JOIN CurriculumDate cd ON cd.CurDateID = cda.CurDateID\n"
                    + "	INNER JOIN Curriculum c ON c.CurID = cd.CurID\n"
                    + "	INNER JOIN SchoolYear_Class syc ON syc.CurID = c.CurID\n"
                    + "	INNER JOIN Teacher_SchoolYear_class tsc ON tsc.Syc_ID = syc.Syc_ID\n"
                    + "	INNER JOIN SchoolYear sy ON sy.SyID = syc.SyID\n"
                    + "	WHERE tsc.TeacherID = ? and cd.DateNumber Like ? \n"
                    + "	AND syc.SyID = (SELECT MAX(SyID) FROM SchoolYear_Class)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            pre.setString(2, "%" + dateNumber + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cdtID = rs.getInt("CdtID");
                String act = rs.getString("Act");
                String timeStart = rs.getString("TimeStart");
                String timeEnd = rs.getString("TimeEnd");
                int curDateID = rs.getInt("CurDateID");
                CurDateAct cda = new CurDateAct(cdtID, act, timeStart, timeEnd, curDateID);
                vector.add(cda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurDateAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}

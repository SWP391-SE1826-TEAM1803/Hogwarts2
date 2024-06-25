package model;

import entity.Curriculum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOCurriculum extends DBConnect {

    public int insertCurriculum(Curriculum curriculum) {
        int n = 0;
        String sql = "INSERT INTO Curriculum (CurName, CateID) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, curriculum.getCurName());
            pre.setInt(2, curriculum.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCurriculum(Curriculum curriculum) {
        int n = 0;
        String sql = "UPDATE Curriculum SET CurName = ?, CateID = ? WHERE CurID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, curriculum.getCurName());
            pre.setInt(2, curriculum.getCateID());
            pre.setInt(3, curriculum.getCurID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCurriculum(int curID) {
        int n = 0;
        String sql = "DELETE FROM Curriculum WHERE CurID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, curID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Curriculum> getAllCurriculum() {
        Vector<Curriculum> vector = new Vector<>();
        String sql = "SELECT * FROM Curriculum";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int curID = rs.getInt("CurID");
                String curName = rs.getString("CurName");
                int cateID = rs.getInt("CateID");
                Curriculum curriculum = new Curriculum(curID, curName, cateID);
                vector.add(curriculum);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Curriculum getCurriculumByID(int curID) {
        Curriculum curriculum = null;
        String sql = "SELECT * FROM Curriculum WHERE CurID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, curID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String curName = rs.getString("CurName");
                int cateID = rs.getInt("CateID");
                curriculum = new Curriculum(curID, curName, cateID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCurriculum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curriculum;
    }
    
    public int getCurCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM [Curriculum]";
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

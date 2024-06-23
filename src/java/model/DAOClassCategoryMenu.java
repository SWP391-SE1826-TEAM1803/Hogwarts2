package model;

import entity.ClassCategoryMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOClassCategoryMenu extends DBConnect {

    public int insertClassCategoryMenu(ClassCategoryMenu ccm) {
        int n = 0;
        String sql = "INSERT INTO ClassCategoryMenu (CateID, MenuID, Date, Meal) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ccm.getCateID());
            pre.setInt(2, ccm.getMenuID());
            pre.setString(3, ccm.getDate());
            pre.setString(4, ccm.getMeal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategoryMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateClassCategoryMenu(ClassCategoryMenu ccm) {
        int n = 0;
        String sql = "UPDATE ClassCategoryMenu SET Date = ?, Meal = ? WHERE CateID = ? AND MenuID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ccm.getDate());
            pre.setString(2, ccm.getMeal());
            pre.setInt(3, ccm.getCateID());
            pre.setInt(4, ccm.getMenuID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategoryMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteClassCategoryMenu(int cateID, int menuID) {
        int n = 0;
        String sql = "DELETE FROM ClassCategoryMenu WHERE CateID = ? AND MenuID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateID);
            pre.setInt(2, menuID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategoryMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<ClassCategoryMenu> getAllClassCategoryMenus() {
        Vector<ClassCategoryMenu> vector = new Vector<>();
        String sql = "SELECT * FROM ClassCategoryMenu";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cateID = rs.getInt("CateID");
                int menuID = rs.getInt("MenuID");
                String date = rs.getString("Date");
                String meal = rs.getString("Meal");
                ClassCategoryMenu ccm = new ClassCategoryMenu(cateID, menuID, date, meal);
                vector.add(ccm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategoryMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public ClassCategoryMenu getClassCategoryMenuByID(int cateID, int menuID) {
        ClassCategoryMenu ccm = null;
        String sql = "SELECT * FROM ClassCategoryMenu WHERE CateID = ? AND MenuID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateID);
            pre.setInt(2, menuID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String date = rs.getString("Date");
                String meal = rs.getString("Meal");
                ccm = new ClassCategoryMenu(cateID, menuID, date, meal);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategoryMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ccm;
    }
}

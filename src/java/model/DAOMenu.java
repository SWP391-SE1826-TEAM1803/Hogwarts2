package model;

import entity.ClassCategory;
import entity.ClassCategoryMenu;
import entity.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOMenu extends DBConnect {

    public Vector<Menu> getAllMenus1() {
        Vector<Menu> menus = new Vector<>();
        String query = "SELECT * FROM Menu";

        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuID(rs.getInt("MenuID"));
                menu.setFood(rs.getString("Food"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public void insertMenu(Menu menu) {
        if (!isDuplicateMenu(menu.getFood())) {
            String sql = "INSERT INTO Menu (Food) VALUES (?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, menu.getFood());
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isDuplicateMenu(String food) {
        String sql = "SELECT * FROM Menu WHERE Food = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, food);
            try (ResultSet rs = pre.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updateMenu(Menu menu) {
        if (!isDuplicateMenu(menu.getFood())) {
            String sql = "UPDATE Menu SET Food = ? WHERE MenuID = ?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, menu.getFood());
                pre.setInt(2, menu.getMenuID());
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int deleteMenu(int menuID) {
        int n = 0;
        String sql = "DELETE FROM Menu WHERE MenuID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, menuID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Menu> getAllMenus() {
        Vector<Menu> vector = new Vector<>();
        String sql = "SELECT * FROM Menu";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int menuID = rs.getInt("MenuID");
                String food = rs.getString("Food");
                Menu menu = new Menu(menuID, food);
                vector.add(menu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Menu> getAllMenus(String sql) {
        Vector<Menu> vector = new Vector<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int menuID = rs.getInt("MenuID");
                String food = rs.getString("Food");
                Menu menu = new Menu(menuID, food);
                vector.add(menu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int getMenuCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM [Menu]";
        try (
                PreparedStatement pre = conn.prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public Menu getMenuByID(int menuID) {
        Menu menu = null;
        String sql = "SELECT * FROM Menu WHERE MenuID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, menuID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String food = rs.getString("Food");
                menu = new Menu(menuID, food);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menu;
    }

    public static void main(String[] args) {
        DAOClassCategory dao = new DAOClassCategory();
        DAOMenu me = new DAOMenu();
        Vector<Menu> vc1 = me.getAllMenus1();
        for (Menu c : vc1) {
            System.out.println(c);
        }
    }
}

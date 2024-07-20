package model;

import entity.ClassCategory;
import entity.ClassCategoryListMenu;
import entity.ClassCategoryMenu;
import entity.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOClassCategoryMenu extends DBConnect {

    public Vector<ClassCategoryMenu> getMenusByCategory(int cateID) {
        Vector<ClassCategoryMenu> classCategoryMenus = new Vector<>();
        String query = "SELECT * FROM ClassCategory_Menu WHERE CateID = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cateID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ClassCategoryMenu menu = new ClassCategoryMenu();
                    menu.setCateID(rs.getInt("CateID"));
                    menu.setMenuID(rs.getInt("MenuID"));
                    menu.setDate(rs.getString("Date"));
                    menu.setMeal(rs.getString("Meal"));
                    classCategoryMenus.add(menu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classCategoryMenus;
    }

    public void addMenuForClass(ClassCategoryMenu classCategoryMenu) {
        String query = "INSERT INTO ClassCategory_Menu (CateID, MenuID, Date, Meal) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, classCategoryMenu.getCateID());
            ps.setInt(2, classCategoryMenu.getMenuID());
            ps.setString(3, classCategoryMenu.getDate());
            ps.setString(4, classCategoryMenu.getMeal());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<ClassCategoryListMenu> getClassCategoryMenuByDate(String date) {
    Vector<ClassCategoryListMenu> classCategoryMenuList = new Vector<>();
    String query = "SELECT cc.CateName, ccm.Date, ccm.Meal, m.Food "
            + "FROM ClassCategory_Menu ccm "
            + "JOIN Menu m ON ccm.MenuID = m.MenuID "
            + "JOIN ClassCategory cc ON ccm.CateID = cc.CateID "
            + "WHERE ccm.Date = ?";
    try {
        // Đảm bảo rằng conn được khởi tạo đúng cách (được kế thừa hoặc thiết lập một cách rõ ràng)
        PreparedStatement pre = conn.prepareStatement(query);
        pre.setString(1, date);
        ResultSet rs = pre.executeQuery();

        Map<String, ClassCategoryListMenu> resultMap = new LinkedHashMap<>(); // Sử dụng LinkedHashMap để duy trì thứ tự chèn

        while (rs.next()) {
            String cateName = rs.getString("CateName");
            String dateStr = rs.getString("Date");
            String meal = rs.getString("Meal");
            String food = rs.getString("Food");

            String key = cateName + "_" + dateStr + "_" + meal;
            ClassCategoryListMenu menu;

            if (resultMap.containsKey(key)) {
                menu = resultMap.get(key);
                menu.getNameFood().add(food);
            } else {
                menu = new ClassCategoryListMenu(cateName, dateStr, meal, new ArrayList<>(Collections.singletonList(food)));
                resultMap.put(key, menu);
            }
        }

        classCategoryMenuList.addAll(resultMap.values());

        conn.close(); // Đóng kết nối sau khi sử dụng (nên sử dụng try-with-resources)
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return classCategoryMenuList;
}


    public int insertClassCategoryMenu(ClassCategoryMenu ccm) {
        int n = 0;
        String sql = "INSERT INTO ClassCategory_Menu (CateID, MenuID, Date, Meal) VALUES (?, ?, ?, ?)";
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

    public int insertClassCategoryMenu1(ClassCategoryMenu ccm) {
        int n = 0;
        String sqlCheck = "SELECT * FROM ClassCategory_Menu WHERE CateID = ? AND MenuID = ? AND Date = ? AND Meal = ?";
        String sqlInsert = "INSERT INTO ClassCategory_Menu (CateID, MenuID, Date, Meal) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preCheck = conn.prepareStatement(sqlCheck);
            preCheck.setInt(1, ccm.getCateID());
            preCheck.setInt(2, ccm.getMenuID());
            preCheck.setString(3, ccm.getDate());
            preCheck.setString(4, ccm.getMeal());
            ResultSet rs = preCheck.executeQuery();
            if (rs.next()) {
                // Entry already exists
                n = -1; // Indicate a duplicate entry
            } else {
                PreparedStatement preInsert = conn.prepareStatement(sqlInsert);
                preInsert.setInt(1, ccm.getCateID());
                preInsert.setInt(2, ccm.getMenuID());
                preInsert.setString(3, ccm.getDate());
                preInsert.setString(4, ccm.getMeal());
                n = preInsert.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategoryMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateClassCategoryMenu(ClassCategoryMenu ccm) {
        int n = 0;
        String sql = "UPDATE ClassCategory_Menu SET Date = ?, Meal = ? WHERE CateID = ? AND MenuID = ?";
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
        String sql = "DELETE FROM ClassCategory_Menu WHERE CateID = ? AND MenuID = ?";
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
        String sql = "SELECT * FROM ClassCategory_Menu";
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

    public Vector<ClassCategoryMenu> getAllClassCategoryMenus(String sql) {
        Vector<ClassCategoryMenu> vector = new Vector<>();
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
        String sql = "SELECT * FROM ClassCategory_Menu WHERE CateID = ? AND MenuID = ?";
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

    public Vector<Menu> getMenusForMeal(int cateID, String meal) {
        Vector<Menu> menus = new Vector<>();
        String query = "SELECT m.MenuID, m.Food FROM Menu m JOIN ClassCategory_Menu cm ON m.MenuID = cm.MenuID WHERE cm.CateID = ? AND cm.Meal = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cateID);
            ps.setString(2, meal);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Menu menu = new Menu();
                    menu.setMenuID(rs.getInt("MenuID"));
                    menu.setFood(rs.getString("Food"));
                    menus.add(menu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public static void main(String[] args) {
        DAOClassCategory dao = new DAOClassCategory();
        DAOClassCategoryMenu dao1 = new DAOClassCategoryMenu();
        Vector<ClassCategoryListMenu> food = dao1.getClassCategoryMenuByDate("2024-07-18");
        for (ClassCategoryListMenu c : food) {
            System.out.println(c);
        }
    }
}

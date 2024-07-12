package model;

import entity.ClassCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOClassCategory extends DBConnect {

    public int insertClassCategory(ClassCategory classCategory) {
        int n = 0;
        String sql = "INSERT INTO ClassCategory (CateName) VALUES (?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, classCategory.getCateName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
      public Vector<ClassCategory> getAllCategories() {
        Vector<ClassCategory> categories = new Vector<>();
        String query = "SELECT * FROM ClassCategory";

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClassCategory category = new ClassCategory();
                category.setCateID(rs.getInt("CateID"));
                category.setCateName(rs.getString("CateName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public int updateClassCategory(ClassCategory classCategory) {
        int n = 0;
        String sql = "UPDATE ClassCategory SET CateName = ? WHERE CateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, classCategory.getCateName());
            pre.setInt(2, classCategory.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteClassCategory(int cateID) {
        int n = 0;
        String sql = "DELETE FROM ClassCategory WHERE CateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<ClassCategory> getAllClassCategories() {
        Vector<ClassCategory> vector = new Vector<>();
        String sql = "SELECT * FROM ClassCategory";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cateID = rs.getInt("CateID");
                String cateName = rs.getString("CateName");
                ClassCategory classCategory = new ClassCategory(cateID, cateName);
                vector.add(classCategory);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public ClassCategory getClassCategoryByID(int cateID) {
        ClassCategory classCategory = null;
        String sql = "SELECT * FROM ClassCategory WHERE CateID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String cateName = rs.getString("CateName");
                classCategory = new ClassCategory(cateID, cateName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classCategory;
    }
    
    public static void main(String[] args) {
        DAOClassCategory dao = new DAOClassCategory();
        Vector<ClassCategory> vc = dao.getAllCategories();
        for(ClassCategory c : vc){
            System.out.println(c);
        }
    }
}

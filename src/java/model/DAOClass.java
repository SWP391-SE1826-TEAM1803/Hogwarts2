package model;

import entity.Class;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOClass extends DBConnect {

    public int insertClass(Class cls) {
        int n = 0;
        String sql = "INSERT INTO Class (ClassName, CateID) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cls.getClassName());
            pre.setInt(2, cls.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateClass(Class cls) {
        int n = 0;
        String sql = "UPDATE Class SET ClassName = ?, CateID = ? WHERE ClassID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cls.getClassName());
            pre.setInt(2, cls.getCateID());
            pre.setInt(3, cls.getClassID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteClass(int classID) {
        int n = 0;
        String sql = "DELETE FROM Class WHERE ClassID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, classID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Class> getAllClasses() {
        Vector<Class> vector = new Vector<>();
        String sql = "SELECT * FROM Class";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int classID = rs.getInt("ClassID");
                String className = rs.getString("ClassName");
                int cateID = rs.getInt("CateID");
                Class cls = new Class(classID, className, cateID);
                vector.add(cls);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Class getClassByID(int classID) {
        Class cls = null;
        String sql = "SELECT * FROM Class WHERE ClassID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, classID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String className = rs.getString("ClassName");
                int cateID = rs.getInt("CateID");
                cls = new Class(classID, className, cateID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cls;
    }
}

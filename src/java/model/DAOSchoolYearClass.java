package model;

import entity.SchoolYearClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSchoolYearClass extends DBConnect {

    public int insertSchoolYearClass(SchoolYearClass schoolYearClass) {
        int n = 0;
        String sql = "INSERT INTO SchoolYearClass (SyID, ClassID, CurID) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, schoolYearClass.getSyID());
            pre.setInt(2, schoolYearClass.getClassID());
            pre.setInt(3, schoolYearClass.getCurID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateSchoolYearClass(SchoolYearClass schoolYearClass) {
        int n = 0;
        String sql = "UPDATE SchoolYearClass SET SyID = ?, ClassID = ?, CurID = ? WHERE SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, schoolYearClass.getSyID());
            pre.setInt(2, schoolYearClass.getClassID());
            pre.setInt(3, schoolYearClass.getCurID());
            pre.setInt(4, schoolYearClass.getSyC_ID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteSchoolYearClass(int syC_ID) {
        int n = 0;
        String sql = "DELETE FROM SchoolYearClass WHERE SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syC_ID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<SchoolYearClass> getAllSchoolYearClasses() {
        Vector<SchoolYearClass> vector = new Vector<>();
        String sql = "SELECT * FROM SchoolYearClass";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int syC_ID = rs.getInt("SyC_ID");
                int syID = rs.getInt("SyID");
                int classID = rs.getInt("ClassID");
                int curID = rs.getInt("CurID");
                SchoolYearClass schoolYearClass = new SchoolYearClass(syC_ID, syID, classID, curID);
                vector.add(schoolYearClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public SchoolYearClass getSchoolYearClassByID(int syC_ID) {
        SchoolYearClass schoolYearClass = null;
        String sql = "SELECT * FROM SchoolYearClass WHERE SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syC_ID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int syID = rs.getInt("SyID");
                int classID = rs.getInt("ClassID");
                int curID = rs.getInt("CurID");
                schoolYearClass = new SchoolYearClass(syC_ID, syID, classID, curID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYearClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schoolYearClass;
    }
}

package model;

import entity.Class;
import entity.StudentClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOClass extends DBConnect {
    
    public boolean isClassNameExists(String className) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            
            String query = "SELECT * FROM Class WHERE ClassName=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, className);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           
        }

        return exists;
    }
     public boolean insertClass1(Class classObj) {
        String sql = "INSERT INTO Class (ClassName, CateID) VALUES (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, classObj.getClassName());
            ps.setInt(2, classObj.getCateID());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
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

    public Vector<Class> getAllClassesWithCategory() {
        Vector<Class> vector = new Vector<>();
        String sql = "SELECT c.ClassID, c.ClassName, cc.CateID, cc.CateName "
                + "FROM Class c JOIN ClassCategory cc ON c.CateID = cc.CateID";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int classID = rs.getInt("ClassID");
                String className = rs.getString("ClassName");
                int cateID = rs.getInt("CateID");
                String cateName = rs.getString("CateName");
                Class cls = new Class(classID, className, cateID, cateName);
                vector.add(cls);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Class> getAllClasses(String sql) {
        Vector<Class> vector = new Vector<>();
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

    public Vector<Class> searchClassesByName(String className) {
        Vector<Class> vector = new Vector<>();
        String sql = "SELECT * FROM Class WHERE ClassName LIKE ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + className + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int classID = rs.getInt("ClassID");
                String name = rs.getString("ClassName");
                int cateID = rs.getInt("CateID");
                Class cls = new Class(classID, name, cateID);
                vector.add(cls);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int getClassCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Class";
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

    public Vector<StudentClass> getStudentsByClassForLatestSchoolYear(int classID) {
        Vector<StudentClass> studentclass = new Vector<>();
        String sql = "SELECT "
                + "    s.StudentID, "
                + "    s.FullName AS StudentName, "
                + "    s.DoB AS DateOfBirth, "
                + "    s.Gender, "
                + "    s.Address, "
                + "    u.Phone AS ParentPhone, "
                + "    u.Email AS ParentEmail, "
                + "    c.ClassName, "
                + "    sy.SyName AS SchoolYear "
                + "FROM "
                + "    Student s "
                + "    INNER JOIN [User] u ON s.UserID = u.UserID "
                + "    INNER JOIN ( "
                + "        SELECT TOP 1 sy.SyID "
                + "        FROM SchoolYear sy "
                + "        ORDER BY sy.SyID DESC "
                + "    ) maxSy ON 1=1 "
                + "    INNER JOIN SchoolYear_Class syc ON maxSy.SyID = syc.SyID "
                + "    INNER JOIN Class c ON syc.ClassID = c.ClassID "
                + "    INNER JOIN Student_SchoolYear_Class ssc ON s.StudentID = ssc.StudentID AND ssc.SyC_ID = syc.SyC_ID "
                + "    INNER JOIN SchoolYear sy ON syc.SyID = sy.SyID "
                + "WHERE "
                + "    c.ClassID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, classID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                String studentName = rs.getString("StudentName");
                String dateOfBirth = rs.getString("DateOfBirth");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                String parentPhone = rs.getString("ParentPhone");
                String parentEmail = rs.getString("ParentEmail");
                String className = rs.getString("ClassName");
                String schoolYear = rs.getString("SchoolYear");
                StudentClass student = new StudentClass(studentID, studentName, dateOfBirth, gender, address, parentPhone, parentEmail, className, schoolYear);
                studentclass.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentclass;
    }

    public Vector<Class> getClassesByTeacherID(int teacherID) {
        Vector<Class> vector = new Vector<>();
        String sql = "SELECT c.ClassID, c.ClassName "
                + "FROM Class c "
                + "INNER JOIN SchoolYear_Class sc ON c.ClassID = sc.ClassID "
                + "INNER JOIN Teacher_SchoolYear_Class tsc ON sc.SyC_ID = tsc.SyC_ID "
                + "INNER JOIN SchoolYear sy ON sc.SyID = sy.SyID "
                + "WHERE tsc.TeacherID = ? AND sy.SyID = (SELECT MAX(SyID) FROM SchoolYear WHERE DateEnd < GETDATE())";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, teacherID);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Class cls = new Class();
                    cls.setClassID(rs.getInt("ClassID"));
                    cls.setClassName(rs.getString("ClassName"));
                    vector.add(cls);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

}

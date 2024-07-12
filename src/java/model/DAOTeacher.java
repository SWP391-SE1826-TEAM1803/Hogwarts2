package model;

import entity.Teacher;
import entity.TeacherAdd;
import entity.TeacherInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTeacher extends DBConnect {

    public int insertTeacher(TeacherAdd teacher) {
        int teacherID = 0;
        String sql = "INSERT INTO [Teacher] (UserID, Degree) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, teacher.getUserID());
            pre.setString(2, teacher.getDegree());
            pre.executeUpdate();

            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) {
                teacherID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teacherID;
    }

    public boolean updateTeacherClass(int teacherID, String newClassName) {
        String sql = "UPDATE Teacher_SchoolYear_Class "
                + "SET SyC_ID = (SELECT SyC_ID FROM SchoolYear_Class WHERE ClassID = (SELECT ClassID FROM Class WHERE ClassName = ?)) "
                + "WHERE TeacherID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newClassName);
            stmt.setInt(2, teacherID);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // If rowsUpdated > 0, update successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Update failed
        }
    }

    public void deleteTeacherFromClass(int teacherID, String className) {
        try {
            // Xóa giáo viên từ lớp học cụ thể
            String deleteTeacherFromClassSQL = "DELETE FROM Teacher_SchoolYear_Class WHERE TeacherID = ? AND SyC_ID IN (SELECT SYC.SyC_ID "
                    + "FROM SchoolYear_Class SYC "
                    + "JOIN Class C ON SYC.ClassID = C.ClassID "
                    + "WHERE C.ClassName = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(deleteTeacherFromClassSQL)) {
                stmt.setInt(1, teacherID);
                stmt.setString(2, className);
                int rowsDeleted = stmt.executeUpdate();
                System.out.println(rowsDeleted + " bản ghi đã được xóa từ Teacher_SchoolYear_Class cho giáo viên dạy lớp " + className + ".");
            } catch (SQLException ex) {
                Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vector<TeacherInfo> getTeacherInfo() {
        Vector<TeacherInfo> teacherInfoVector = new Vector<>();
        String sql = "SELECT "
                + "    t.TeacherID, "
                + "    u.FullName AS TeacherName, "
                + "    c.ClassID, "
                + "    c.ClassName AS ClassName, "
                + "    syc.SyID, "
                + "    t.Degree "
                + "FROM "
                + "    Teacher t "
                + "    INNER JOIN [User] u ON t.UserID = u.UserID "
                + "    LEFT JOIN Teacher_SchoolYear_Class tsc ON t.TeacherID = tsc.TeacherID "
                + "    LEFT JOIN SchoolYear_Class syc ON tsc.SyC_ID = syc.SyC_ID "
                + "    LEFT JOIN Class c ON syc.ClassID = c.ClassID";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int teacherID = rs.getInt("TeacherID");
                String teacherName = rs.getString("TeacherName");
                int classID = rs.getInt("ClassID");
                String className = rs.getString("ClassName");
                int syID = rs.getInt("SyID");
                String degree = rs.getString("Degree");
                TeacherInfo teacherInfo = new TeacherInfo(teacherID, teacherName, classID, className, syID, degree);
                teacherInfoVector.add(teacherInfo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return teacherInfoVector;
    }

    public int updateTeacher(Teacher teacher) {
        int n = 0;
        String sql = "UPDATE Teacher SET UserID = ?, Degree = ? WHERE TeacherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacher.getUserID());
            pre.setString(2, teacher.getDegree());
            pre.setInt(3, teacher.getTeacherID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteTeacher(int teacherID) {
        int n = 0;
        String sql = "DELETE FROM Teacher WHERE TeacherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Teacher> getAllTeachers() {
        Vector<Teacher> vector = new Vector<>();
        String sql = "SELECT * FROM Teacher";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int teacherID = rs.getInt("TeacherID");
                int userID = rs.getInt("UserID");
                String degree = rs.getString("Degree");
                Teacher teacher = new Teacher(teacherID, userID, degree);
                vector.add(teacher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Teacher> getAllTeachers(String sql) {
        Vector<Teacher> vector = new Vector<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int teacherID = rs.getInt("TeacherID");
                int userID = rs.getInt("UserID");
                String degree = rs.getString("Degree");
                Teacher teacher = new Teacher(teacherID, userID, degree);
                vector.add(teacher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int getTeacherCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Teacher";
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

    public Teacher getTeacherByID(int teacherID) {
        Teacher teacher = null;
        String sql = "SELECT * FROM Teacher WHERE TeacherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("UserID");
                String degree = rs.getString("Degree");
                teacher = new Teacher(teacherID, userID, degree);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teacher;
    }

    public int getTeacherIDByUserID(int userID) {
        int teacherID = -1;
        String sql = "SELECT TeacherID FROM Teacher WHERE UserID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                teacherID = rs.getInt("TeacherID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teacherID;
    }
}

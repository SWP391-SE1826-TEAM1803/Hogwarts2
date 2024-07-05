package model;

import entity.Student;
import entity.StudentSchoolYearClass;
import entity.Class;
import entity.SchoolYearClass;
import entity.User;
import entity.SchoolYear;
import entity.Student1;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStudent extends DBConnect {

    public Vector<StudentSchoolYearClass> getAllStudents() {
        Vector<StudentSchoolYearClass> vector = new Vector<>();
        String sql = "SELECT s.StudentID, s.FullName, s.DoB, s.Gender, s.Address, s.UserID, c.ClassID, c.ClassName, u.FullName as ParentName, sy.SyName "
                + "FROM Student s "
                + "INNER JOIN Student_SchoolYear_Class ssc ON s.StudentID = ssc.StudentID "
                + "INNER JOIN SchoolYear_Class sc ON ssc.SyC_ID = sc.SyC_ID "
                + "INNER JOIN Class c ON sc.ClassID = c.ClassID "
                + "INNER JOIN [User] u ON s.UserID = u.UserID  "
                + "INNER JOIN SchoolYear sy ON sc.SyID = sy.SyID";
        try (PreparedStatement pre = conn.prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("StudentID"));
                student.setFullName(rs.getString("FullName"));
                student.setDoB(rs.getDate("DoB"));
                student.setGender(rs.getString("Gender"));
                student.setAddress(rs.getString("Address"));

                User parent = new User();
                parent.setUserID(rs.getInt("UserID"));
                parent.setFullName(rs.getString("ParentName"));
                student.setParent(parent);

                Class cl = new Class();
                cl.setClassID(rs.getInt("ClassID"));
                cl.setClassName(rs.getString("ClassName"));

                SchoolYear sy = new SchoolYear();
                sy.setSyName(rs.getString("SyName"));

                SchoolYearClass syc = new SchoolYearClass();
                syc.setClassObj(cl);
                syc.setSchoolYear(sy);

                StudentSchoolYearClass stuClass = new StudentSchoolYearClass();
                stuClass.setStudent(student);
                stuClass.setSchoolYearClass(syc);

                vector.add(stuClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<StudentSchoolYearClass> getAllStudentsByYear(String year) {
        Vector<StudentSchoolYearClass> vector = new Vector<>();
        String sql = "SELECT s.StudentID, s.FullName, s.DoB, s.Gender, s.Address, s.UserID, c.ClassID, c.ClassName, u.FullName as ParentName, sy.SyName "
                + "FROM Student s "
                + "FULL JOIN Student_SchoolYear_Class ssc ON s.StudentID = ssc.StudentID "
                + "FULL JOIN SchoolYear_Class sc ON ssc.SyC_ID = sc.SyC_ID "
                + "FULL JOIN Class c ON sc.ClassID = c.ClassID "
                + "INNER JOIN [User] u ON s.UserID = u.UserID AND u.Role = 'Parent' "
                + "FULL JOIN SchoolYear sy ON sc.SyID = sy.SyID "
                + "WHERE sy.SyName = ?"; // Using parameterized query

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, year);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentID(rs.getInt("StudentID"));
                    student.setFullName(rs.getString("FullName"));
                    student.setDoB(rs.getDate("DoB"));
                    student.setGender(rs.getString("Gender"));
                    student.setAddress(rs.getString("Address"));

                    User parent = new User();
                    parent.setUserID(rs.getInt("UserID"));
                    parent.setFullName(rs.getString("ParentName"));
                    student.setParent(parent);

                    Class cl = new Class();
                    cl.setClassID(rs.getInt("ClassID"));
                    cl.setClassName(rs.getString("ClassName"));

                    SchoolYear sy = new SchoolYear();
                    sy.setSyName(rs.getString("SyName"));

                    SchoolYearClass syc = new SchoolYearClass();
                    syc.setClassObj(cl);
                    syc.setSchoolYear(sy);

                    StudentSchoolYearClass stuClass = new StudentSchoolYearClass();
                    stuClass.setStudent(student);
                    stuClass.setSchoolYearClass(syc);

                    vector.add(stuClass);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public void addStudent(Student student, int classID) {
        String insertStudentSQL = "INSERT INTO Student (FullName, DoB, Gender, Address, UserID) VALUES (?, ?, ?, ?, ?)";
        String insertSSC_SQL = "INSERT INTO Student_SchoolYear_Class (StudentID, SyC_ID) "
                + "SELECT ?, SyC_ID FROM SchoolYear_Class WHERE ClassID = ? AND SyID = (SELECT MAX(SyID) FROM SchoolYear_Class WHERE ClassID = ?)";

        try {
            // Insert student into Student table
            try (PreparedStatement pre = conn.prepareStatement(insertStudentSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pre.setString(1, student.getFullName());
                pre.setDate(2, new java.sql.Date(student.getDoB().getTime()));
                pre.setString(3, student.getGender());
                pre.setString(4, student.getAddress());
                pre.setInt(5, student.getUserID());
                pre.executeUpdate();

                try (ResultSet rs = pre.getGeneratedKeys()) {
                    if (rs.next()) {
                        int studentID = rs.getInt(1);

                        // Insert into Student_SchoolYear_Class
                        try (PreparedStatement pre2 = conn.prepareStatement(insertSSC_SQL)) {
                            pre2.setInt(1, studentID);
                            pre2.setInt(2, classID);
                            pre2.setInt(3, classID); // Added this parameter for the nested SELECT
                            pre2.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StudentSchoolYearClass getStudentById(int studentID) {
        StudentSchoolYearClass studentClass = null;
        String sql = "SELECT s.StudentID, s.FullName, s.DoB, s.Gender, s.Address, s.UserID, c.ClassID, c.ClassName, u.FullName as ParentName, sy.SyName "
                + "FROM Student s "
                + "INNER JOIN Student_SchoolYear_Class ssc ON s.StudentID = ssc.StudentID "
                + "INNER JOIN SchoolYear_Class sc ON ssc.SyC_ID = sc.SyC_ID "
                + "INNER JOIN Class c ON sc.ClassID = c.ClassID "
                + "INNER JOIN [User] u ON s.UserID = u.UserID "
                + "INNER JOIN SchoolYear sy ON sc.SyID = sy.SyID "
                + "WHERE s.StudentID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, studentID);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setStudentID(rs.getInt("StudentID"));
                    student.setFullName(rs.getString("FullName"));
                    student.setDoB(rs.getDate("DoB"));
                    student.setGender(rs.getString("Gender"));
                    student.setAddress(rs.getString("Address"));

                    User parent = new User();
                    parent.setUserID(rs.getInt("UserID"));
                    parent.setFullName(rs.getString("ParentName"));
                    student.setParent(parent);

                    Class cl = new Class();
                    cl.setClassID(rs.getInt("ClassID"));
                    cl.setClassName(rs.getString("ClassName"));

                    SchoolYear sy = new SchoolYear();
                    sy.setSyName(rs.getString("SyName"));

                    SchoolYearClass syc = new SchoolYearClass();
                    syc.setClassObj(cl);
                    syc.setSchoolYear(sy);

                    studentClass = new StudentSchoolYearClass();
                    studentClass.setStudent(student);
                    studentClass.setSchoolYearClass(syc);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentClass;
    }

    public void updateStudent(Student student, int classID) {
        String updateStudentSQL = "UPDATE Student SET FullName = ?, DoB = ?, Gender = ?, Address = ?, UserID = ? WHERE StudentID = ?";
        String updateSSC_SQL = "UPDATE Student_SchoolYear_Class SET SyC_ID = (SELECT SyC_ID FROM SchoolYear_Class WHERE ClassID = ? AND SyID = (SELECT MAX(SyID) FROM SchoolYear_Class WHERE ClassID = ?)) WHERE StudentID = ?";

        try {
            // Update student in Student table
            try (PreparedStatement pre = conn.prepareStatement(updateStudentSQL)) {
                pre.setString(1, student.getFullName());
                pre.setDate(2, new java.sql.Date(student.getDoB().getTime()));
                pre.setString(3, student.getGender());
                pre.setString(4, student.getAddress());
                pre.setInt(5, student.getUserID());
                pre.setInt(6, student.getStudentID());
                pre.executeUpdate();
            }

            // Update Student_SchoolYear_Class
            try (PreparedStatement pre = conn.prepareStatement(updateSSC_SQL)) {
                pre.setInt(1, classID);
                pre.setInt(2, classID);
                pre.setInt(3, student.getStudentID());
                pre.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStudent(int studentID) {
        String deleteStudentSQL = "DELETE FROM Student WHERE StudentID = ?";
        String deleteSSC_SQL = "DELETE FROM Student_SchoolYear_Class WHERE StudentID = ?";

        try {
            // Delete from Student_SchoolYear_Class
            try (PreparedStatement pre = conn.prepareStatement(deleteSSC_SQL)) {
                pre.setInt(1, studentID);
                pre.executeUpdate();
            }

            // Delete from Student table
            try (PreparedStatement pre = conn.prepareStatement(deleteStudentSQL)) {
                pre.setInt(1, studentID);
                pre.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
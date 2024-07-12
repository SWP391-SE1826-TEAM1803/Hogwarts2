package model;

import entity.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFeedback extends DBConnect {

    public Vector<Feedback> getAllFeedbacks(String sql) {
        Vector<Feedback> vector = new Vector<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feedbackID = rs.getInt("FeedbackID");
                java.sql.Date date = rs.getDate("Date");
                String content = rs.getString("Content");
                int studentID = rs.getInt("StudentID");
                Feedback feedback = new Feedback(feedbackID, date, content, studentID);
                vector.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Feedback getFeedbackByID(int feedbackID) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE FeedbackID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, feedbackID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                java.sql.Date date = rs.getDate("Date");
                String content = rs.getString("Content");
                int studentID = rs.getInt("StudentID");
                feedback = new Feedback(feedbackID, date, content, studentID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedback;
    }

    public Vector<Feedback> getAllFeedback(int teacherID) {
    Vector<Feedback> feedbacks = new Vector<>();
    String sql = "SELECT f.FeedbackID, f.Date, f.Content, f.StudentID, s.FullName AS StudentName "
                + "FROM Feedback f "
                + "INNER JOIN Student_SchoolYear_Class ssc ON f.StudentID = ssc.StudentID "
                + "INNER JOIN SchoolYear_Class syc ON ssc.SyC_ID = syc.SyC_ID "
                + "INNER JOIN Teacher_SchoolYear_Class tsc ON syc.SyC_ID = tsc.SyC_ID "
                + "INNER JOIN SchoolYear sy ON syc.SyID = sy.SyID "
                + "INNER JOIN Student s ON f.StudentID = s.StudentID "
                + "WHERE tsc.TeacherID = ? AND sy.SyID = (SELECT MAX(SyID) FROM SchoolYear WHERE DateEnd < GETDATE()) "
                + "AND f.Date = (SELECT MAX(Date) FROM Feedback WHERE StudentID = f.StudentID)";

    try (PreparedStatement pre = conn.prepareStatement(sql)) {
        pre.setInt(1, teacherID);
        try (ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setDate(rs.getDate("Date"));
                feedback.setContent(rs.getString("Content"));
                feedback.setStudentID(rs.getInt("StudentID"));
                feedback.setFullName(rs.getString("StudentName"));  // Assuming Feedback entity has setStudentName method

                feedbacks.add(feedback);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
    }
    return feedbacks;
}


    public boolean isDateExistsForStudent(int teacherID, Date date) {
        String sql = "SELECT 1 FROM Feedback f "
                + "INNER JOIN Student_SchoolYear_Class ssc ON f.StudentID = ssc.StudentID "
                + "INNER JOIN SchoolYear_Class syc ON ssc.SyC_ID = syc.SyC_ID "
                + "INNER JOIN Teacher_SchoolYear_Class tsc ON syc.SyC_ID = tsc.SyC_ID "
                + "WHERE tsc.TeacherID = ? AND f.Date = ? ";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, teacherID);
            pre.setDate(2, (java.sql.Date) date);
            try (ResultSet rs = pre.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void addFeedbackForAllStudents(int teacherID, java.sql.Date date, String content) {
        if (!isDateExistsForStudent(teacherID, date)) {
            String insertSql = "INSERT INTO Feedback (Date, Content, StudentID) "
                    + "SELECT ?, ?, ssc.StudentID "
                    + "FROM Student_SchoolYear_Class ssc "
                    + "INNER JOIN SchoolYear_Class syc ON ssc.SyC_ID = syc.SyC_ID "
                    + "INNER JOIN Teacher_SchoolYear_Class tsc ON syc.SyC_ID = tsc.SyC_ID "
                    + "INNER JOIN SchoolYear sy ON syc.SyID = sy.SyID "
                    + "WHERE tsc.TeacherID = ? AND sy.SyID = (SELECT MAX(SyID) FROM SchoolYear WHERE DateEnd < GETDATE())";

            try (PreparedStatement insertPre = conn.prepareStatement(insertSql)) {
                insertPre.setDate(1, date);
                insertPre.setString(2, content);
                insertPre.setInt(3, teacherID);
                insertPre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Feedback getFeedbackByIDUpdate(int id) {
    Feedback feedback = null;
    String sql = "SELECT f.FeedbackID, f.Date, f.Content, f.StudentID, s.FullName AS StudentName " +
                 "FROM Feedback f " +
                 "INNER JOIN Student s ON f.StudentID = s.StudentID " +
                 "WHERE f.FeedbackID = ?";
    
    try (PreparedStatement pre = conn.prepareStatement(sql)) {
        pre.setInt(1, id);
        try (ResultSet rs = pre.executeQuery()) {
            if (rs.next()) {
                feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setDate(rs.getDate("Date"));
                feedback.setContent(rs.getString("Content"));
                feedback.setStudentID(rs.getInt("StudentID"));
                feedback.setFullName(rs.getString("StudentName")); // Assuming Feedback entity has setFullName method
               
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return feedback;
}


    public void updateFeedback(int id, Date date, String content) {
        String sql = "UPDATE Feedback SET Date = ?, Content = ? WHERE FeedbackID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setDate(1, (java.sql.Date) date);
            pre.setString(2, content);
            pre.setInt(3, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

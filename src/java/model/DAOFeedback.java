package model;

import entity.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFeedback extends DBConnect {

    public int insertFeedback(Feedback feedback) {
        int n = 0;
        String sql = "INSERT INTO Feedback (Date, Content, StudentID) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, feedback.getDate());
            pre.setString(2, feedback.getContent());
            pre.setInt(3, feedback.getStudentID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateFeedback(Feedback feedback) {
        int n = 0;
        String sql = "UPDATE Feedback SET Date = ?, Content = ?, StudentID = ? WHERE FeedbackID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, feedback.getDate());
            pre.setString(2, feedback.getContent());
            pre.setInt(3, feedback.getStudentID());
            pre.setInt(4, feedback.getFeedbackID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteFeedback(int feedbackID) {
        int n = 0;
        String sql = "DELETE FROM Feedback WHERE FeedbackID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, feedbackID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Feedback> getAllFeedbacks() {
        Vector<Feedback> vector = new Vector<>();
        String sql = "SELECT * FROM Feedback";
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
}

package entity;

import java.sql.Date;

public class Feedback {
    private int FeedbackID;
    private Date Date;
    private String Content;
    private int StudentID;

    public Feedback() {
    }

    public Feedback(int FeedbackID, Date Date, String Content, int StudentID) {
        this.FeedbackID = FeedbackID;
        this.Date = Date;
        this.Content = Content;
        this.StudentID = StudentID;
    }

    public int getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(int FeedbackID) {
        this.FeedbackID = FeedbackID;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }
}

package entity;

public class Teacher {
    private int TeacherID;
    private int UserID;
    private String Degree;

    public Teacher() {
    }

    public Teacher(int TeacherID, int UserID, String Degree) {
        this.TeacherID = TeacherID;
        this.UserID = UserID;
        this.Degree = Degree;
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int TeacherID) {
        this.TeacherID = TeacherID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String Degree) {
        this.Degree = Degree;
    }
}

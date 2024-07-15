package entity;

public class TeacherAdd {
   
    private String userID;
    private String degree;

    public TeacherAdd() {
    }

    public TeacherAdd( String userID, String degree) {
        
        this.userID = userID;
        this.degree = degree;
    }

    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

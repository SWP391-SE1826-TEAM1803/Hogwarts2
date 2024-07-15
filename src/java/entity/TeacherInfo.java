package entity;
public class TeacherInfo {
    private int teacherID;
    private String teacherName;
    private int classID;
    private String className;
    private int syID;
    private String degree;

    // Constructor
    public TeacherInfo(int teacherID, String teacherName, int classID, String className, int syID, String degree) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.classID = classID;
        this.className = className;
        this.syID = syID;
        this.degree = degree;
    }

    // Getters and setters for all fields
    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getSyID() {
        return syID;
    }

    public void setSyID(int syID) {
        this.syID = syID;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

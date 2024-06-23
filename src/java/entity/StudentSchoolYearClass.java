package entity;

public class StudentSchoolYearClass {
    private int StudentID;
    private int SyC_ID;

    public StudentSchoolYearClass() {
    }

    public StudentSchoolYearClass(int StudentID, int SyC_ID) {
        this.StudentID = StudentID;
        this.SyC_ID = SyC_ID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public int getSyC_ID() {
        return SyC_ID;
    }

    public void setSyC_ID(int SyC_ID) {
        this.SyC_ID = SyC_ID;
    }
}

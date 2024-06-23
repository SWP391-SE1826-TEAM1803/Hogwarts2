package entity;

public class TeacherSchoolYearClass {
    private int TeacherID;
    private int SyC_ID;

    public TeacherSchoolYearClass() {
    }

    public TeacherSchoolYearClass(int TeacherID, int SyC_ID) {
        this.TeacherID = TeacherID;
        this.SyC_ID = SyC_ID;
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int TeacherID) {
        this.TeacherID = TeacherID;
    }

    public int getSyC_ID() {
        return SyC_ID;
    }

    public void setSyC_ID(int SyC_ID) {
        this.SyC_ID = SyC_ID;
    }
}

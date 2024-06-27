package entity;

public class StudentSchoolYearClass {
    private int StudentID;
    private int SyC_ID;
    private Student student;
    private SchoolYearClass schoolYearClass;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolYearClass getSchoolYearClass() {
        return schoolYearClass;
    }

    public void setSchoolYearClass(SchoolYearClass schoolYearClass) {
        this.schoolYearClass = schoolYearClass;
    }
}

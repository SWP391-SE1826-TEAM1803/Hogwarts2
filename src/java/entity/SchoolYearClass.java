package entity;

public class SchoolYearClass {
    private int SyC_ID;
    private int SyID;
    private int ClassID;
    private int CurID;
    private Class classObj; // Added to hold the Class object
    private SchoolYear schoolYear; // Added to hold the SchoolYear object

    public SchoolYearClass() {
    }

    public SchoolYearClass(int SyC_ID, int SyID, int ClassID, int CurID) {
        this.SyC_ID = SyC_ID;
        this.SyID = SyID;
        this.ClassID = ClassID;
        this.CurID = CurID;
    }

    public SchoolYearClass(int SyC_ID, Class classObj, SchoolYear schoolYear, int CurID) {
        this.SyC_ID = SyC_ID;
        this.classObj = classObj;
        this.schoolYear = schoolYear;
        if (classObj != null) {
            this.ClassID = classObj.getClassID();
        }
        if (schoolYear != null) {
            this.SyID = schoolYear.getSyID();
        }
        this.CurID = CurID;
    }

    public int getSyC_ID() {
        return SyC_ID;
    }

    public void setSyC_ID(int SyC_ID) {
        this.SyC_ID = SyC_ID;
    }

    public int getSyID() {
        return SyID;
    }

    public void setSyID(int SyID) {
        this.SyID = SyID;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public int getCurID() {
        return CurID;
    }

    public void setCurID(int CurID) {
        this.CurID = CurID;
    }

    public Class getClassObj() {
        return classObj;
    }

    public void setClassObj(Class classObj) {
        this.classObj = classObj;
        if (classObj != null) {
            this.ClassID = classObj.getClassID(); // Update ClassID from Class object
        }
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
        if (schoolYear != null) {
            this.SyID = schoolYear.getSyID(); // Update SyID from SchoolYear object
        }
    }
}

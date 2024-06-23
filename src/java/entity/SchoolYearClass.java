package entity;

public class SchoolYearClass {
    private int SyC_ID;
    private int SyID;
    private int ClassID;
    private int CurID;

    public SchoolYearClass() {
    }

    public SchoolYearClass(int SyC_ID, int SyID, int ClassID, int CurID) {
        this.SyC_ID = SyC_ID;
        this.SyID = SyID;
        this.ClassID = ClassID;
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
}

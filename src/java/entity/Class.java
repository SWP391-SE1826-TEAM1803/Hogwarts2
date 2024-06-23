package entity;

public class Class {
    private int ClassID;
    private String ClassName;
    private int CateID;

    public Class() {
    }

    public Class(int ClassID, String ClassName, int CateID) {
        this.ClassID = ClassID;
        this.ClassName = ClassName;
        this.CateID = CateID;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }
}

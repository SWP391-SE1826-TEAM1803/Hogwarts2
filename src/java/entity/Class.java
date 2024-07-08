package entity;

public class Class {
    private int classID;
    private String className;
    private int cateID;
    private String cateName;

    public Class() {
    }
    
    public Class(String className, int cateID) {
        this.className = className;
        this.cateID = cateID;
    }

    public Class(int classID, String className, int cateID) {
        this.classID = classID;
        this.className = className;
        this.cateID = cateID;
    }

    public Class(int classID, String className, int cateID, String cateName) {
        this.classID = classID;
        this.className = className;
        this.cateID = cateID;
        this.cateName = cateName;
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

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}

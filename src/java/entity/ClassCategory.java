package entity;

public class ClassCategory {
    private int CateID;
    private String CateName;

    public ClassCategory() {
    }

    public ClassCategory(int CateID, String CateName) {
        this.CateID = CateID;
        this.CateName = CateName;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }
}

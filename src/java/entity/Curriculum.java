package entity;

public class Curriculum {
    private int CurID;
    private String CurName;
    private int CateID;

    public Curriculum() {
    }

    public Curriculum(int CurID, String CurName, int CateID) {
        this.CurID = CurID;
        this.CurName = CurName;
        this.CateID = CateID;
    }

    public Curriculum(String CurName, int CateID) {
        this.CurName = CurName;
        this.CateID = CateID;
    }
    
    

    public int getCurID() {
        return CurID;
    }

    public void setCurID(int CurID) {
        this.CurID = CurID;
    }

    public String getCurName() {
        return CurName;
    }

    public void setCurName(String CurName) {
        this.CurName = CurName;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }
}

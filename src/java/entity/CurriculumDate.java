package entity;

public class CurriculumDate {

    private int CurDateID;
    private String DateNumber;
    private int CurID;
    private int CateID;
    private String CurName;

    public CurriculumDate() {
    }

    public CurriculumDate(int CurDateID, String DateNumber, int CurID, String CurName) {
        this.CurDateID = CurDateID;
        this.DateNumber = DateNumber;
        this.CurID = CurID;
        this.CurName = CurName;
    }

    public CurriculumDate(int CurDateID, String DateNumber, int CurID) {
        this.CurDateID = CurDateID;
        this.DateNumber = DateNumber;
        this.CurID = CurID;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public String getCurName() {
        return CurName;
    }

    public void setCurName(String CurName) {
        this.CurName = CurName;
    }

    public int getCurDateID() {
        return CurDateID;
    }

    public void setCurDateID(int CurDateID) {
        this.CurDateID = CurDateID;
    }

    public String getDateNumber() {
        return DateNumber;
    }

    public void setDateNumber(String DateNumber) {
        this.DateNumber = DateNumber;
    }

    public int getCurID() {
        return CurID;
    }

    public void setCurID(int CurID) {
        this.CurID = CurID;
    }
}

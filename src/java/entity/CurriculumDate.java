package entity;

public class CurriculumDate {
    private int CurDateID;
    private String DateNumber;
    private int CurID;

    public CurriculumDate() {
    }

    public CurriculumDate(int CurDateID, String DateNumber, int CurID) {
        this.CurDateID = CurDateID;
        this.DateNumber = DateNumber;
        this.CurID = CurID;
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

package entity;

public class CurDateAct {
    private int CdtID;
    private String Act;
    private String TimeStart;
    private String TimeEnd;
    private int CurDateID;
    private String DateNumber;
    
    

    public CurDateAct() {
    }

    public CurDateAct(int CdtID, String Act, String TimeStart, String TimeEnd, int CurDateID) {
        this.CdtID = CdtID;
        this.Act = Act;
        this.TimeStart = TimeStart;
        this.TimeEnd = TimeEnd;
        this.CurDateID = CurDateID;
    }

    public CurDateAct(int CdtID, String Act, String TimeStart, String TimeEnd, int CurDateID, String DateNumber) {
        this.CdtID = CdtID;
        this.Act = Act;
        this.TimeStart = TimeStart;
        this.TimeEnd = TimeEnd;
        this.CurDateID = CurDateID;
        this.DateNumber = DateNumber;
    }

    public String getDateNumber() {
        return DateNumber;
    }

    public void setDateNumber(String DateNumber) {
        this.DateNumber = DateNumber;
    }
    
    
    

    
    
    

    public int getCdtID() {
        return CdtID;
    }

    public void setCdtID(int CdtID) {
        this.CdtID = CdtID;
    }

    public String getAct() {
        return Act;
    }

    public void setAct(String Act) {
        this.Act = Act;
    }

    public String getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(String TimeStart) {
        this.TimeStart = TimeStart;
    }

    public String getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(String TimeEnd) {
        this.TimeEnd = TimeEnd;
    }

    public int getCurDateID() {
        return CurDateID;
    }

    public void setCurDateID(int CurDateID) {
        this.CurDateID = CurDateID;
    }
}

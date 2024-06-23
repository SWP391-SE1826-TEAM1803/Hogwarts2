package entity;

import java.sql.Date;

public class SchoolYear {
    private int SyID;
    private String SyName;
    private Date DateStart;
    private Date DateEnd;

    public SchoolYear() {
    }

    public SchoolYear(int SyID, String SyName, Date DateStart, Date DateEnd) {
        this.SyID = SyID;
        this.SyName = SyName;
        this.DateStart = DateStart;
        this.DateEnd = DateEnd;
    }

    public int getSyID() {
        return SyID;
    }

    public void setSyID(int SyID) {
        this.SyID = SyID;
    }

    public String getSyName() {
        return SyName;
    }

    public void setSyName(String SyName) {
        this.SyName = SyName;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date DateStart) {
        this.DateStart = DateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date DateEnd) {
        this.DateEnd = DateEnd;
    }
}

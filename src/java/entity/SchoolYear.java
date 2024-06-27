package entity;

import java.sql.Date;

public class SchoolYear {
    private int SyID;
    private String SyName;
    private String DateStart;
    private String DateEnd;

    public SchoolYear() {
    }

    public SchoolYear(int SyID, String SyName, String DateStart, String DateEnd) {
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

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String DateStart) {
        this.DateStart = DateStart;
    }

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String DateEnd) {
        this.DateEnd = DateEnd;
    }
}

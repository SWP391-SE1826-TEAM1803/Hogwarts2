package entity;

import java.sql.Date;

public class Schedules {
    private int SchedulesID;
    private String Date;
    private int CurDateID;
    private int SyC_ID;

    public Schedules() {
    }

    public Schedules(int SchedulesID, String Date, int CurDateID, int SyC_ID) {
        this.SchedulesID = SchedulesID;
        this.Date = Date;
        this.CurDateID = CurDateID;
        this.SyC_ID = SyC_ID;
    }

    public int getSchedulesID() {
        return SchedulesID;
    }

    public void setSchedulesID(int SchedulesID) {
        this.SchedulesID = SchedulesID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getCurDateID() {
        return CurDateID;
    }

    public void setCurDateID(int CurDateID) {
        this.CurDateID = CurDateID;
    }

    public int getSyC_ID() {
        return SyC_ID;
    }

    public void setSyC_ID(int SyC_ID) {
        this.SyC_ID = SyC_ID;
    }
}

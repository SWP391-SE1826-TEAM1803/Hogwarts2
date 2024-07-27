package model;

import entity.Schedules;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSchedules extends DBConnect {

    public void insertSchedule(Schedules schedule) {
        if (!isDuplicateDateAndCurDate(schedule.getDate(), schedule.getCurDateID(), schedule.getSyC_ID())
            &&!isDuplicateDate(schedule.getDate(),schedule.getSyC_ID())
            &&!isDuplicateCurDate(schedule.getCurDateID(), schedule.getSyC_ID())
            ) {
            String sql = "INSERT INTO Schedules (Date, CurDateID, SyC_ID) VALUES (?, ?, ?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, schedule.getDate());
                pre.setInt(2, schedule.getCurDateID());
                pre.setInt(3, schedule.getSyC_ID());
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateSchedule(Schedules schedule) {
        if (!isDuplicateDateAndCurDate(schedule.getDate(), schedule.getCurDateID(), schedule.getSyC_ID())
                &&!isDuplicateCurDate(schedule.getCurDateID(), schedule.getSyC_ID())) {
            
            String sql = "UPDATE Schedules SET Date = ?, CurDateID = ?, SyC_ID = ? WHERE SchedulesID = ?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, schedule.getDate());
                pre.setInt(2, schedule.getCurDateID());
                pre.setInt(3, schedule.getSyC_ID());
                pre.setInt(4, schedule.getSchedulesID());
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Existing isDuplicateDate method
    public boolean isDuplicateDateAndCurDate(String date, int curDateID, int syC_ID) {
        String sql = "SELECT * FROM Schedules WHERE Date = ? AND CurDateID = ? AND SyC_ID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, date);
            pre.setInt(2, curDateID);
            pre.setInt(3, syC_ID);
            try (ResultSet rs = pre.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean isDuplicateDate(String date, int syC_ID) {
        String sql = "SELECT * FROM Schedules WHERE Date = ? AND SyC_ID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, date);
            pre.setInt(2, syC_ID);
            try (ResultSet rs = pre.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isDuplicateCurDate(int curDateID, int syC_ID) {
        String sql = "SELECT * FROM Schedules WHERE CurDateID = ? AND SyC_ID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, curDateID);
            pre.setInt(2, syC_ID);
            try (ResultSet rs = pre.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    public int updateSchedule1(Schedules schedule) {
//        int n = 0;
//        String sql = "UPDATE Schedules SET Date = ?, CurDateID = ?, SyC_ID = ? WHERE SchedulesID = ?";
//        try {
//            PreparedStatement pre = conn.prepareStatement(sql);
//            pre.setString(1, schedule.getDate());
//            pre.setInt(2, schedule.getCurDateID());
//            pre.setInt(3, schedule.getSyC_ID());
//            pre.setInt(4, schedule.getSchedulesID());
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return n;
//    }

    public int deleteSchedule(int schedulesID) {
        int n = 0;
        String sql = "DELETE FROM Schedules WHERE SchedulesID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, schedulesID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Schedules> getAllSchedulesBySycID(int syC_ID) {
        Vector<Schedules> vector = new Vector<>();
        String sql = "SELECT * FROM Schedules JOIN CurriculumDate ON Schedules.CurDateID = CurriculumDate.CurDateID WHERE Schedules.SyC_ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syC_ID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int schedulesID = rs.getInt("SchedulesID");
                String date = rs.getString("Date");
                int curDateID = rs.getInt("CurDateID");
                String dateNumber = rs.getString("DateNumber");
                Schedules schedule = new Schedules(schedulesID, date, curDateID, syC_ID, dateNumber);
                vector.add(schedule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Schedules> getAllSchedules(String sql) {
        Vector<Schedules> vector = new Vector<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int schedulesID = rs.getInt("SchedulesID");
                String date = rs.getString("Date");
                int curDateID = rs.getInt("CurDateID");
                int syC_ID = rs.getInt("SyC_ID");
                Schedules schedule = new Schedules(schedulesID, date, curDateID, syC_ID);
                vector.add(schedule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Schedules> getAllSchedules() {
        Vector<Schedules> vector = new Vector<>();
        String sql = "Select * from Schedules";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int schedulesID = rs.getInt("SchedulesID");
                String date = rs.getString("Date");
                int curDateID = rs.getInt("CurDateID");
                int syC_ID = rs.getInt("SyC_ID");
                String dateNumber = rs.getString("DateNumber");
                Schedules schedule = new Schedules(schedulesID, date, curDateID, syC_ID, dateNumber);
                vector.add(schedule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Schedules getScheduleByID(int schedulesID) {
        Schedules schedule = null;
        String sql = "SELECT * FROM Schedules WHERE SchedulesID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, schedulesID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String date = rs.getString("Date");
                int curDateID = rs.getInt("CurDateID");
                int syC_ID = rs.getInt("SyC_ID");
                schedule = new Schedules(schedulesID, date, curDateID, syC_ID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedule;
    }

    public static void main(String[] args) {
        DAOSchedules daos = new DAOSchedules();
        Vector<Schedules> x = daos.getAllSchedulesBySycID(1);
        for (Schedules e : x) {
            System.out.println(e);
        }
    }

    public Vector<Schedules> getAllSchedulesBySycIDAndTeacherID(int teacherID) {
        Vector<Schedules> vector = new Vector<>();
        String sql = "SELECT \n"
                + "    sch.*, cd.DateNumber\n"
                + "FROM schedules sch\n"
                + "INNER JOIN CurriculumDate cd ON cd.CurDateID = sch.CurDateID\n"
                + "INNER JOIN SchoolYear_Class syc ON syc.Syc_ID = sch.Syc_ID\n"
                + "INNER JOIN Teacher_SchoolYear_class tsc ON tsc.Syc_ID = syc.Syc_ID\n"
                + "INNER JOIN SchoolYear sy ON sy.SyID = syc.SyID\n"
                + "WHERE \n"
                + "    tsc.TeacherID = ?\n"
                + "    AND syc.SyID = (SELECT MAX(SyID) FROM SchoolYear_Class)\n"
                + "    AND sch.Date >= sy.DateStart \n"
                + "    AND sch.Date <= sy.DateEnd;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    int schedulesID = rs.getInt("SchedulesID");
                    String date = rs.getString("Date");
                    int curDateID = rs.getInt("CurDateID");
                    int syC_ID = rs.getInt("SyC_ID");
                    String dateNumber = rs.getString("DateNumber");
                    Schedules schedule = new Schedules(schedulesID, date, curDateID, syC_ID, dateNumber);
                    vector.add(schedule);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Schedules> getAllSchedulesBySycIDAndTeacherID(int teacherID, String dateS) {
        Vector<Schedules> vector = new Vector<>();
        String sql = "SELECT \n"
                + "    sch.*, cd.DateNumber\n"
                + "FROM schedules sch\n"
                + "INNER JOIN CurriculumDate cd ON cd.CurDateID = sch.CurDateID\n"
                + "INNER JOIN SchoolYear_Class syc ON syc.Syc_ID = sch.Syc_ID\n"
                + "INNER JOIN Teacher_SchoolYear_class tsc ON tsc.Syc_ID = syc.Syc_ID\n"
                + "INNER JOIN SchoolYear sy ON sy.SyID = syc.SyID\n"
                + "WHERE \n"
                + "    tsc.TeacherID = ? AND sch.Date = ? \n"
                + "    AND syc.SyID = (SELECT MAX(SyID) FROM SchoolYear_Class)\n"
                + "    AND sch.Date >= sy.DateStart \n"
                + "    AND sch.Date <= sy.DateEnd;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            pre.setString(2, dateS);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    int schedulesID = rs.getInt("SchedulesID");
                    String date = rs.getString("Date");
                    int curDateID = rs.getInt("CurDateID");
                    int syC_ID = rs.getInt("SyC_ID");
                    String dateNumber = rs.getString("DateNumber");
                    Schedules schedule = new Schedules(schedulesID, date, curDateID, syC_ID, dateNumber);
                    vector.add(schedule);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Schedules> getAllSchedulesByDateAndTeacherID(int teacherID, java.sql.Date dateS) {
        Vector<Schedules> vector = new Vector<>();
        String sql = "SELECT \n"
                + "    sch.*, cd.DateNumber\n"
                + "FROM schedules sch\n"
                + "INNER JOIN CurriculumDate cd ON cd.CurDateID = sch.CurDateID\n"
                + "INNER JOIN SchoolYear_Class syc ON syc.Syc_ID = sch.Syc_ID\n"
                + "INNER JOIN Teacher_SchoolYear_class tsc ON tsc.Syc_ID = syc.Syc_ID\n"
                + "INNER JOIN SchoolYear sy ON sy.SyID = syc.SyID\n"
                + "WHERE \n"
                + "    tsc.TeacherID = ? AND sch.Date = ?\n"
                + "    AND syc.SyID = (SELECT MAX(SyID) FROM SchoolYear_Class)\n"
                + "    AND sch.Date >= sy.DateStart \n"
                + "    AND sch.Date <= sy.DateEnd;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, teacherID);
            pre.setDate(2, dateS);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    int schedulesID = rs.getInt("SchedulesID");
                    String date = rs.getString("Date");
                    int curDateID = rs.getInt("CurDateID");
                    int syC_ID = rs.getInt("SyC_ID");
                    String dateNumber = rs.getString("DateNumber");
                    Schedules schedule = new Schedules(schedulesID, date, curDateID, syC_ID, dateNumber);
                    vector.add(schedule);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchedules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

}

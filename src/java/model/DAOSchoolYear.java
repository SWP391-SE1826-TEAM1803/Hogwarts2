package model;

import entity.SchoolYear;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSchoolYear extends DBConnect {

    public int insertSchoolYear(SchoolYear schoolYear) {
        SchoolYear lastSchoolYear = getLastSchoolYear();
        if (isOverlapping(schoolYear, lastSchoolYear)) {
            return -1; // Return -1 if the date range is overlapping
        }
        try {
            String sql = "INSERT INTO SchoolYear (SyName, DateStart, DateEnd) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, schoolYear.getSyName());
            ps.setString(2, schoolYear.getDateStart());
            ps.setString(3, schoolYear.getDateEnd());
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int newSyID = rs.getInt(1);
                    if (lastSchoolYear != null) {
                        copyClassesFromLastSchoolYear(lastSchoolYear.getSyID(), newSyID);
                    }
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public SchoolYear getLastSchoolYear() {
        try {
            String sql = "SELECT * FROM SchoolYear ORDER BY SyID DESC OFFSET 0 ROW FETCH NEXT 1 ROW ONLY;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SchoolYear(rs.getInt("SyID"), rs.getString("SyName"), rs.getString("DateStart"), rs.getString("DateEnd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void copyClassesFromLastSchoolYear(int lastSyID, int newSyID) {
    try {
        // Copy classes from the last school year
        String sqlClasses = "INSERT INTO SchoolYear_Class (SyID, ClassID, CurID) SELECT ?, ClassID, CurID FROM SchoolYear_Class WHERE SyID = ?";
        PreparedStatement psClasses = conn.prepareStatement(sqlClasses);
        psClasses.setInt(1, newSyID);
        psClasses.setInt(2, lastSyID);
        psClasses.executeUpdate();

        // Get the newly created SyC_IDs for the new school year
        String sqlNewSyC_IDs = "SELECT SyC_ID, ClassID, CurID FROM SchoolYear_Class WHERE SyID = ?";
        PreparedStatement psNewSyC_IDs = conn.prepareStatement(sqlNewSyC_IDs);
        psNewSyC_IDs.setInt(1, newSyID);
        ResultSet rsNewSyC_IDs = psNewSyC_IDs.executeQuery();

        while (rsNewSyC_IDs.next()) {
            int newSyC_ID = rsNewSyC_IDs.getInt("SyC_ID");
            int classID = rsNewSyC_IDs.getInt("ClassID");
            int curID = rsNewSyC_IDs.getInt("CurID");

            // Copy teachers from the last school year to the new school year
            String sqlTeachers = "INSERT INTO Teacher_SchoolYear_Class (TeacherID, SyC_ID) " +
                                  "SELECT TeacherID, ? FROM Teacher_SchoolYear_Class " +
                                  "JOIN SchoolYear_Class ON Teacher_SchoolYear_Class.SyC_ID = SchoolYear_Class.SyC_ID " +
                                  "WHERE SchoolYear_Class.SyID = ? AND SchoolYear_Class.ClassID = ? AND SchoolYear_Class.CurID = ?";
            PreparedStatement psTeachers = conn.prepareStatement(sqlTeachers);
            psTeachers.setInt(1, newSyC_ID);
            psTeachers.setInt(2, lastSyID);
            psTeachers.setInt(3, classID);
            psTeachers.setInt(4, curID);
            psTeachers.executeUpdate();
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public int updateSchoolYear(SchoolYear schoolYear) {
       
        int n = 0;
        String sql = "UPDATE [dbo].[SchoolYear] SET [SyName] = ?, [DateStart] = ?, [DateEnd] = ? WHERE [SyID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, schoolYear.getSyName());
            pre.setString(2, schoolYear.getDateStart());
            pre.setString(3, schoolYear.getDateEnd());
            pre.setInt(4, schoolYear.getSyID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeSchoolYear(int syID) {
        try {
            // Delete related records from SchoolYear_Class
            String sqlDeleteClasses = "DELETE FROM SchoolYear_Class WHERE SyID = ?";
            PreparedStatement psDeleteClasses = conn.prepareStatement(sqlDeleteClasses);
            psDeleteClasses.setInt(1, syID);
            psDeleteClasses.executeUpdate();

            // Delete the SchoolYear record
            String sqlDeleteSchoolYear = "DELETE FROM SchoolYear WHERE SyID = ?";
            PreparedStatement psDeleteSchoolYear = conn.prepareStatement(sqlDeleteSchoolYear);
            psDeleteSchoolYear.setInt(1, syID);
            return psDeleteSchoolYear.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;    }

    public Vector<SchoolYear> getAllSchoolYears(String sql) {
        Vector<SchoolYear> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int syID = rs.getInt(1);
                String syName = rs.getString(2);
                String dateStart = rs.getString(3);
                String dateEnd = rs.getString(4);
                SchoolYear schoolYear = new SchoolYear(syID, syName, dateStart, dateEnd);
                vector.add(schoolYear);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public SchoolYear getSchoolYearByID(int syID) {
        SchoolYear schoolYear = null;
        String sql = "SELECT * FROM [dbo].[SchoolYear] WHERE [SyID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, syID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String syName = rs.getString("SyName");
                String dateStart = rs.getString("DateStart");
                String dateEnd = rs.getString("DateEnd");
                schoolYear = new SchoolYear(syID, syName, dateStart, dateEnd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSchoolYear.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schoolYear;
    }

    private boolean isOverlapping(SchoolYear schoolYear, SchoolYear lastSchoolYear) {
        if (lastSchoolYear != null) {
            // Check if the start date of the new school year is after the end date of the last school year
            if (schoolYear.getDateStart().compareTo(lastSchoolYear.getDateEnd()) > 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DAOSchoolYear dao = new DAOSchoolYear();
        int n = dao.insertSchoolYear(new SchoolYear(0, "2025-2026", "2024-09-02", "2025-06-29"));
        if (n > 0) {
            System.out.println("Inserted");
        } else if (n == -1) {
            System.out.println("Date range is overlapping");
        }
        Vector<SchoolYear> vector = dao.getAllSchoolYears("SELECT * FROM SchoolYear");
        for (SchoolYear schoolYear : vector) {
            System.out.println(schoolYear.getSyID() + " - " + schoolYear.getSyName() + " - " + schoolYear.getDateStart() + " - " + schoolYear.getDateEnd());
        }
    }
}

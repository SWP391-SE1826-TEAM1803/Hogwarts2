package entity;

import java.sql.Date;

public class Student {
    private int StudentID;
    private String FullName;
    private Date DoB;
    private String Gender;
    private String Address;
    private int UserID;

    public Student() {
    }

    public Student(int StudentID, String FullName, Date DoB, String Gender, String Address, int UserID) {
        this.StudentID = StudentID;
        this.FullName = FullName;
        this.DoB = DoB;
        this.Gender = Gender;
        this.Address = Address;
        this.UserID = UserID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date DoB) {
        this.DoB = DoB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
}

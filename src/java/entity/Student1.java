// Student.java
package entity;

import java.sql.Date;

public class Student1 {
    private int StudentID;
    private String FullName;
    private String DoB;
    private String Gender;
    private String Address;
    private int UserID;

    public Student1() {}

    public Student1(int StudentID, String FullName, String DoB, String Gender, String Address, int UserID) {
        this.StudentID = StudentID;
        this.FullName = FullName;
        this.DoB = DoB;
        this.Gender = Gender;
        this.Address = Address;
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

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String DoB) {
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

package entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DuyLinh04
 */
public class TeacherInfoOfClass {
     private String teacherName;
    private String degree;
    private String className;

    public TeacherInfoOfClass() {
    }

    public TeacherInfoOfClass(String teacherName, String degree, String className) {
        this.teacherName = teacherName;
        this.degree = degree;
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    
}

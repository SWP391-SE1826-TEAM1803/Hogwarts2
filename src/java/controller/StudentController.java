package controller;

import entity.Class;
import entity.Student;
import entity.StudentSchoolYearClass;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOClass;
import model.DAOStudent;
import model.DAOUser;

import java.io.IOException;
import java.sql.Date;
import java.util.Vector;

@WebServlet(name = "StudentController", urlPatterns = {"/StudentControllerURL"})
public class StudentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStudent dao = new DAOStudent();
        DAOClass daoClass = new DAOClass();
        DAOUser daoUser = new DAOUser();
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("listAll")) {
            String year = request.getParameter("year");
            Vector<StudentSchoolYearClass> students;

            if (year == null || year.isEmpty()) {
                students = dao.getAllStudents();
            } else {
                students = dao.getAllStudentsByYear(year);
            }
            request.setAttribute("data", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("StudentManager.jsp");
            dispatcher.forward(request, response);
        } else if (service.equals("showAddStudent")) {
            Vector<Class> classes = daoClass.getAllClasses();
            Vector<User> users = daoUser.getAllParents();
            request.setAttribute("classes", classes);
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("AddStudent.jsp");
            dispatcher.forward(request, response);
        } else if (service.equals("addStudent")) {
            String fullName = request.getParameter("fullName");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            int classID = Integer.parseInt(request.getParameter("classID"));
            int userID = Integer.parseInt(request.getParameter("userID"));

            Student student = new Student();
            student.setFullName(fullName);
            student.setDoB(Date.valueOf(dob));
            student.setGender(gender);
            student.setAddress(address);
            student.setUserID(userID);

            dao.addStudent(student, classID);
            
            response.sendRedirect("StudentControllerURL?service=listAll");
        } else if (service.equals("showUpdateStudent")) {
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            StudentSchoolYearClass studentClass = dao.getStudentById(studentID);
            Vector<Class> classes = daoClass.getAllClasses();
            Vector<User> users = daoUser.getAllParents();
            request.setAttribute("studentClass", studentClass);
            request.setAttribute("classes", classes);
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateStudent.jsp");
            dispatcher.forward(request, response);
        } else if (service.equals("updateStudent")) {
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            String fullName = request.getParameter("fullName");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            int classID = Integer.parseInt(request.getParameter("classID"));
            int userID = Integer.parseInt(request.getParameter("userID"));

            Student student = new Student();
            student.setStudentID(studentID);
            student.setFullName(fullName);
            student.setDoB(Date.valueOf(dob));
            student.setGender(gender);
            student.setAddress(address);
            student.setUserID(userID);

            dao.updateStudent(student, classID);
            response.sendRedirect("StudentControllerURL?service=listAll");
        } else if (service.equals("deleteStudent")) {
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            dao.deleteStudent(studentID);
            response.sendRedirect("StudentControllerURL?service=listAll");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

// File: controller/StudentSchoolYearClassController.java
package controller;

import entity.SchoolYear;
import entity.StudentSchoolYearClass;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOClass;
import model.DAOSchoolYear;
import model.DAOStudentSchoolYearClass;

@WebServlet(name = "StudentSYearClassController", urlPatterns = {"/StudentSYClassControllerURL"})
public class StudentSYClassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStudentSchoolYearClass dao = new DAOStudentSchoolYearClass();

        DAOClass daoC = new DAOClass();
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

//        if (service.equals("addStudentSchoolYearClass")) {
//            // Get data from request
//            String StudentID = request.getParameter("StudentID");
//            String SyC_ID = request.getParameter("SyC_ID");
//            // Create StudentSchoolYearClass object
//            StudentSchoolYearClass ssClass = new StudentSchoolYearClass(StudentID, SyC_ID);
//            // Insert StudentSchoolYearClass
//            dao.insertStudentSchoolYearClass(ssClass);
//            // Redirect to listAll
//            response.sendRedirect("StudentSchoolYearClassControllerURL?service=listAll");
//        }

        if (service.equals("listAll")) {
            // Get all student school year classes
            Vector<StudentSchoolYearClass> vector = dao.getAllStudentSchoolYearClasses("SELECT * FROM Student_SchoolYear_Class");
            // Set data to request
            request.setAttribute("data", vector);
            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("StudentSchoolYearClassList.jsp");
            dispatcher.forward(request, response);
        }

//        if (service.equals("updateStudentSchoolYearClass")) {
//            String submit = request.getParameter("submit");
//            if (submit == null) {
//                // Show update form
//                String studentID = request.getParameter("StudentID");
//                String syC_ID = request.getParameter("SyC_ID");
//                StudentSchoolYearClass ssClass = dao.getStudentSchoolYearClassByID(studentID, syC_ID);
//                request.setAttribute("ssClass", ssClass);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateStudentSchoolYearClass.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                // Update student school year class data
//                String StudentID = request.getParameter("StudentID");
//                String SyC_ID = request.getParameter("SyC_ID");
//                StudentSchoolYearClass ssClass = new StudentSchoolYearClass(StudentID, SyC_ID);
//                dao.updateStudentSchoolYearClass(ssClass);
//                response.sendRedirect("StudentSchoolYearClassControllerURL?service=listAll");
//            }
//        }

//        if (service.equals("deleteStudentSchoolYearClass")) {
//            String studentID = request.getParameter("StudentID");
//            String syC_ID = request.getParameter("SyC_ID");
//            dao.deleteStudentSchoolYearClass(studentID, syC_ID);
//            response.sendRedirect("StudentSchoolYearClassControllerURL?service=listAll");
//        }
        
        if (service.equals("viewSYClass")) {
            String SyC_ID = request.getParameter("SyC_ID");
            String SyID = request.getParameter("SyID");

            String cID = request.getParameter("cID");
            Vector<StudentSchoolYearClass> vector;       
        
            vector = dao.getAllStudentSchoolYearClasses("SELECT * FROM [Student_SchoolYear_Class] WHERE SyC_ID = '" + SyC_ID + "'");

            Vector <entity.Class> classx = daoC.getAllClasses("SELECT * FROM [Class] WHERE ClassID = '" + cID + "'");
                        request.setAttribute("syID", SyID);

            String cName = classx.get(0).getClassName();
            request.setAttribute("cName", cName);
            request.setAttribute("data", vector);
            RequestDispatcher dispatcher = request.getRequestDispatcher("SchoolYearClassDetail.jsp");
            dispatcher.forward(request, response);
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

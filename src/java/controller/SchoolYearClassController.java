package controller;

import entity.SchoolYearClass;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOSchoolYearClass;

@WebServlet(name = "SchoolYearClassController", urlPatterns = {"/SchoolYearClassControllerURL"})
public class SchoolYearClassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOSchoolYearClass dao = new DAOSchoolYearClass();
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

//        if (service.equals("addSchoolYearClass")) {
//            // Get data from request
//            String SyC_ID = request.getParameter("SyC_ID");
//            String SyID = request.getParameter("SyID");
//            String ClassID = request.getParameter("ClassID");
//            String CurID = request.getParameter("CurID");
//            // Create SchoolYearClass object
//            SchoolYearClass syClass = new SchoolYearClass(SyC_ID, SyID, ClassID, CurID);
//            // Insert SchoolYearClass
//            dao.insertSchoolYearClass(syClass);
//            // Redirect to listAll
//            response.sendRedirect("SchoolYearClassControllerURL?service=listAll");
//        }

        if (service.equals("listAll")) {
            // Get all school year classes
            Vector<SchoolYearClass> vector = dao.getAllSchoolYearClasses("SELECT * FROM SchoolYearClass");
            // Set data to request
            request.setAttribute("data", vector);
            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("SchoolYearClassList.jsp");
            dispatcher.forward(request, response);
        }

//        if (service.equals("updateSchoolYearClass")) {
//            String submit = request.getParameter("submit");
//            if (submit == null) {
//                // Show update form
//                String syC_ID = request.getParameter("SyC_ID");
//                SchoolYearClass syClass = dao.getSchoolYearClassByID(syC_ID);
//                request.setAttribute("syClass", syClass);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateSchoolYearClass.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                // Update school year class data
//                String SyC_ID = request.getParameter("SyC_ID");
//                String SyID = request.getParameter("SyID");
//                String ClassID = request.getParameter("ClassID");
//                String CurID = request.getParameter("CurID");
//                SchoolYearClass syClass = new SchoolYearClass(SyC_ID, SyID, ClassID, CurID);
//                dao.updateSchoolYearClass(syClass);
//                response.sendRedirect("SchoolYearClassControllerURL?service=listAll");
//            }
//        }

//        if (service.equals("deleteSchoolYearClass")) {
//            String syC_ID = request.getParameter("SyC_ID");
//            dao.deleteSchoolYearClass(syC_ID);
//            response.sendRedirect("SchoolYearClassControllerURL?service=listAll");
//        }
        
        if (service.equals("searchBySyID")) {
            String syID = request.getParameter("SyID");            
            // Get all school year classes
            Vector<SchoolYearClass> vector = dao.getAllSchoolYearClasses("SELECT * FROM SchoolYear_Class Where SyID = '" + syID + "'");
            // Set data to request
            request.setAttribute("data", vector);
            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("SchoolYearDetail.jsp");
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

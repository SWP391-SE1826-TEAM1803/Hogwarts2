package controller;

import entity.SchoolYear;
import entity.SchoolYearClass;
import entity.TeacherSchoolYearClass;
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
import model.DAOSchoolYear;
import model.DAOSchoolYearClass;
import model.DAOTeacherSchoolYearClass;

@WebServlet(name = "SchoolYearClassController", urlPatterns = {"/SchoolYearClassControllerURL"})
public class SchoolYearClassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOSchoolYearClass dao = new DAOSchoolYearClass();
        HttpSession session = request.getSession(true);
        DAOSchoolYear daoSY = new DAOSchoolYear();
        DAOTeacherSchoolYearClass daoTSC = new DAOTeacherSchoolYearClass();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("addSchoolYearClass")) {
            // Get data from request
           // Get data from request
                int SyID = Integer.parseInt(request.getParameter("SyID"));
                int ClassID = Integer.parseInt(request.getParameter("ClassID"));
                int CurID = Integer.parseInt(request.getParameter("CurID"));
                int TeacherID = Integer.parseInt(request.getParameter("TeacherID"));

                // Create SchoolYearClass object
                SchoolYearClass syClass = new SchoolYearClass(0, SyID, ClassID, CurID);
                // Insert SchoolYearClass
                dao.insertSchoolYearClass(syClass);
                SchoolYearClass newSyC = dao.getAllSchoolYearClasses("Select * from SchoolYear_Class ORDER BY SyC_ID DESC;").get(0);
                // Insert TeacherSchoolYearClass
                TeacherSchoolYearClass teacherSyClass = new TeacherSchoolYearClass(TeacherID, newSyC.getSyC_ID());
                daoTSC.insertTeacherSchoolYearClass(teacherSyClass);

                // Redirect to listAll
                response.sendRedirect("SchoolYearClassControllerURL?service=searchBySyID&SyID=" + SyID);

        }

        if (service.equals("listAll")) {
            // Get all school year classes
            Vector<SchoolYearClass> vector = dao.getAllSchoolYearClasses("SELECT * FROM SchoolYearClass");
            // Set data to request
            request.setAttribute("data", vector);
            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("SchoolYearClassList.jsp");
            dispatcher.forward(request, response);
        }

         if (service.equals("updateSchoolYearClass")) {
            // Update school year class data
            String syID = request.getParameter("SyID");
            int SyC_ID = Integer.parseInt(request.getParameter("SyC_ID"));
            int CurID = Integer.parseInt(request.getParameter("CurID"));
            int TeacherID = Integer.parseInt(request.getParameter("TeacherID"));
            SchoolYearClass syClass = new SchoolYearClass(SyC_ID, 0, 0, CurID);
            dao.updateSchoolYearClass(syClass);
            TeacherSchoolYearClass teacherSyClass = new TeacherSchoolYearClass(TeacherID, SyC_ID);
            daoTSC.updateTeacherSchoolYearClass(teacherSyClass);
            response.sendRedirect("SchoolYearClassControllerURL?service=searchBySyID&SyID=" + syID);
        }


        if (service.equals("deleteSchoolYearClass")) {
            String syID = request.getParameter("SyID");            
            
            int syC_ID = Integer.parseInt(request.getParameter("SyC_ID"));

            dao.deleteSchoolYearClass(syC_ID);
            response.sendRedirect("SchoolYearClassControllerURL?service=searchBySyID&SyID="+syID);
        }
        
        if (service.equals("searchBySyID")) {
            String syID = request.getParameter("SyID");     
             Vector<SchoolYear> sy = daoSY.getAllSchoolYears("SELECT * FROM SchoolYear Where SyID = '" + syID + "'");
             String syname = sy.get(0).getSyName();
            request.setAttribute("syname", syname);
request.setAttribute("syID", syID);
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

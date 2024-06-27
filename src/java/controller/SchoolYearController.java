package controller;

import entity.SchoolYear;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOSchoolYear;

@WebServlet(name = "SchoolYearController", urlPatterns = {"/SchoolYearControllerURL"})
public class SchoolYearController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOSchoolYear dao = new DAOSchoolYear();
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        switch (service) {
            case "addSchoolYear":
                String syName = request.getParameter("SyName");
                String dateStart = request.getParameter("DateStart");
                String dateEnd = request.getParameter("DateEnd");
                SchoolYear schoolYear = new SchoolYear(0, syName, dateStart, dateEnd);
                int result = dao.insertSchoolYear(schoolYear);
                if (result > 0) {
                    response.sendRedirect("SchoolYearControllerURL?service=listAll");
                } else if (result == -1) {
                    request.setAttribute("error", "Date range is overlapping. Please enter again.");
                    request.setAttribute("schoolYear", schoolYear);
                    RequestDispatcher addDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=addSchoolYearForm");
                    addDispatcher.forward(request, response);
                }
                break;

            case "listAll":
                Vector<SchoolYear> vector = dao.getAllSchoolYears("SELECT * FROM SchoolYear");
                request.setAttribute("data", vector);
                RequestDispatcher dispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp");
                dispatcher.forward(request, response);
                break;

            case "updateSY":
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String syID = request.getParameter("SyID");
                    SchoolYear schoolYearToUpdate = dao.getSchoolYearByID(Integer.parseInt(syID));
                    request.setAttribute("schoolYear", schoolYearToUpdate);
                    RequestDispatcher updateDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=updateSchoolYearForm");
                    updateDispatcher.forward(request, response);
                } else {
                    String syID = request.getParameter("SyID");
                    syName = request.getParameter("SyName");
                    dateStart = request.getParameter("DateStart");
                    dateEnd = request.getParameter("DateEnd");
                    SchoolYear updatedSchoolYear = new SchoolYear(Integer.parseInt(syID), syName, dateStart, dateEnd);
                    int updateResult = dao.updateSchoolYear(updatedSchoolYear);
                    if (updateResult > 0) {
                        response.sendRedirect("SchoolYearControllerURL?service=listAll");
                    } else if (updateResult == -1) {
                        request.setAttribute("error", "Date range is overlapping. Please enter again.");
                        request.setAttribute("schoolYear", updatedSchoolYear);
                        RequestDispatcher updateDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=updateSchoolYearForm");
                        updateDispatcher.forward(request, response);
                    }
                }
                break;

            case "deleteSY":
                String syID = request.getParameter("SyID");
                dao.removeSchoolYear(Integer.parseInt(syID));
                response.sendRedirect("SchoolYearControllerURL?service=listAll");
                break;

            case "addSchoolYearForm":
                RequestDispatcher addDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=addSchoolYearForm");
                addDispatcher.forward(request, response);
                break;

            case "updateSchoolYearForm":
                String syIDToUpdate = request.getParameter("SyID");
                SchoolYear schoolYearToUpdateForm = dao.getSchoolYearByID(Integer.parseInt(syIDToUpdate));
                request.setAttribute("schoolYear", schoolYearToUpdateForm);
                RequestDispatcher updateFormDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=updateSchoolYearForm");
                updateFormDispatcher.forward(request, response);
                break;

            default:
                response.sendRedirect("SchoolYearControllerURL?service=listAll");
                break;
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

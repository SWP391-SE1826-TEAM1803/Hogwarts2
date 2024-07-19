package controller;

import entity.SchoolYear;
import model.DAOSchoolYear;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

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
                addSchoolYear(request, response, dao);
                break;

            case "listAll":
                listAllSchoolYears(request, response, dao);
                break;

            case "updateSY":
                updateSchoolYear(request, response, dao);
                break;

            case "deleteSY":
                deleteSchoolYear(request, response, dao);
                break;

            case "addSchoolYearForm":
                showAddSchoolYearForm(request, response);
                break;

            case "updateSchoolYearForm":
                showUpdateSchoolYearForm(request, response, dao);
                break;

            default:
                listAllSchoolYears(request, response, dao);
                break;
        }
    }

    private void addSchoolYear(HttpServletRequest request, HttpServletResponse response, DAOSchoolYear dao)
            throws ServletException, IOException {
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
    }

    private void listAllSchoolYears(HttpServletRequest request, HttpServletResponse response, DAOSchoolYear dao)
            throws ServletException, IOException {
        Vector<SchoolYear> vector = dao.getAllSchoolYears("SELECT * FROM SchoolYear");
        request.setAttribute("data", vector);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp");
        dispatcher.forward(request, response);
    }

    private void updateSchoolYear(HttpServletRequest request, HttpServletResponse response, DAOSchoolYear dao)
            throws ServletException, IOException {
        String syID = request.getParameter("SyID");
        String syName = request.getParameter("SyName");
        String dateStart = request.getParameter("DateStart");
        String dateEnd = request.getParameter("DateEnd");
        SchoolYear schoolYear = new SchoolYear(Integer.parseInt(syID), syName, dateStart, dateEnd);
        int result = dao.updateSchoolYear(schoolYear);
        if (result > 0) {
            response.sendRedirect("SchoolYearControllerURL?service=listAll");
        } else if (result == -1) {
            request.setAttribute("error", "Date range is overlapping. Please enter again.");
            request.setAttribute("schoolYear", schoolYear);
            RequestDispatcher updateDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=updateSchoolYearForm");
            updateDispatcher.forward(request, response);
        }
    }

    private void deleteSchoolYear(HttpServletRequest request, HttpServletResponse response, DAOSchoolYear dao)
            throws IOException {
        String syID = request.getParameter("SyID");
        dao.removeSchoolYear(Integer.parseInt(syID));
        response.sendRedirect("SchoolYearControllerURL?service=listAll");
    }

    private void showAddSchoolYearForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher addDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=addSchoolYearForm");
        addDispatcher.forward(request, response);
    }

    private void showUpdateSchoolYearForm(HttpServletRequest request, HttpServletResponse response, DAOSchoolYear dao)
            throws ServletException, IOException {
        String syID = request.getParameter("SyID");
        SchoolYear schoolYear = dao.getSchoolYearByID(Integer.parseInt(syID));
        request.setAttribute("schoolYear", schoolYear);
        RequestDispatcher updateDispatcher = request.getRequestDispatcher("ManageSchoolYear.jsp?service=updateSchoolYearForm");
        updateDispatcher.forward(request, response);
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
        return "School Year Management Controller";
    }
}

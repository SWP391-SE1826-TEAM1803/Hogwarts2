package controller;

import entity.CurriculumDate;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCurriculumDate;

@WebServlet(name = "CurriculumDateController", urlPatterns = {"/CurriculumDateControllerURL"})
public class CurriculumDateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCurriculumDate dao = new DAOCurriculumDate();
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("addCurriculumDate")) {
            String dateNumber = request.getParameter("DateNumber");
            int curID = Integer.parseInt(request.getParameter("CurID"));
            CurriculumDate curriculumDate = new CurriculumDate(dateNumber, curID);
            dao.insertCurriculumDate(curriculumDate);
            response.sendRedirect("CurriculumDateControllerURL?service=listAll");
        }

        if (service.equals("listAll")) {
            Vector<CurriculumDate> vector = dao.getAllCurriculumDates();
            request.setAttribute("data", vector);
            RequestDispatcher dispatcher = request.getRequestDispatcher("CurriculumDateManager.jsp");
            dispatcher.forward(request, response);
        }

        if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int curDateID = Integer.parseInt(request.getParameter("CurDateID"));
                CurriculumDate curriculumDate = dao.getCurriculumDateByID(curDateID);
                request.setAttribute("curriculumDate", curriculumDate);
                RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateCurriculumDate.jsp");
                dispatcher.forward(request, response);
            } else {
                int curDateID = Integer.parseInt(request.getParameter("CurDateID"));
                String dateNumber = request.getParameter("DateNumber");
                int curID = Integer.parseInt(request.getParameter("CurID"));
                CurriculumDate curriculumDate = new CurriculumDate(curDateID, dateNumber, curID);
                dao.updateCurriculumDate(curriculumDate);
                response.sendRedirect("CurriculumDateControllerURL?service=listAll");
            }
        }

        if (service.equals("delete")) {
            int curDateID = Integer.parseInt(request.getParameter("CurDateID"));
            dao.deleteCurriculumDate(curDateID);
            response.sendRedirect("CurriculumDateControllerURL?service=listAll");
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
        return "Curriculum Date Controller";
    }
}

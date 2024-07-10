package controller;

import entity.CurDateAct;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCurDateAct;

@WebServlet(name = "CurDateActController", urlPatterns = {"/CurDateActControllerURL"})
public class CurDateActController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCurDateAct dao = new DAOCurDateAct();
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("addCurDateAct")) {
            String act = request.getParameter("Act");
            String timeStart = request.getParameter("TimeStart");
            String timeEnd = request.getParameter("TimeEnd");
            int curDateID = Integer.parseInt(request.getParameter("CurDateID"));
            CurDateAct curDateAct = new CurDateAct(0, act, timeStart, timeEnd, curDateID);
            dao.insertCurDateAct(curDateAct);
            response.sendRedirect("CurDateActControllerURL?service=listAll");
        }

        if (service.equals("listAll")) {
            Vector<CurDateAct> vector = dao.getAllCurDateActs();
            request.setAttribute("data", vector);
            RequestDispatcher dispatcher = request.getRequestDispatcher("CurDateActManager.jsp");
            dispatcher.forward(request, response);
        }

        if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int cdtID = Integer.parseInt(request.getParameter("CdtID"));
                CurDateAct curDateAct = dao.getCurDateActByID(cdtID);
                request.setAttribute("curDateAct", curDateAct);
                RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateCurDateAct.jsp");
                dispatcher.forward(request, response);
            } else {
                int cdtID = Integer.parseInt(request.getParameter("CdtID"));
                String act = request.getParameter("Act");
                String timeStart = request.getParameter("TimeStart");
                String timeEnd = request.getParameter("TimeEnd");
                int curDateID = Integer.parseInt(request.getParameter("CurDateID"));
                CurDateAct curDateAct = new CurDateAct(cdtID, act, timeStart, timeEnd, curDateID);
                dao.updateCurDateAct(curDateAct);
                response.sendRedirect("CurDateActControllerURL?service=listAll");
            }
        }

        if (service.equals("delete")) {
            int cdtID = Integer.parseInt(request.getParameter("CdtID"));
            dao.deleteCurDateAct(cdtID);
            response.sendRedirect("CurDateActControllerURL?service=listAll");
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
        return "CurDateAct Controller";
    }
}

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
                int curID = Integer.parseInt(request.getParameter("CurID"));
                response.sendRedirect("CurriculumDateControllerURL?service=searchByID&CurID=" + curID);
        }

        if (service.equals("listAll")) {
            Vector<CurDateAct> vector = dao.getAllCurDateActs();
            request.setAttribute("data", vector);
            RequestDispatcher dispatcher = request.getRequestDispatcher("CurDateActManager.jsp");
            dispatcher.forward(request, response);
        }

        if (service.equals("update")) {
                        int curID = Integer.parseInt(request.getParameter("CurID"));
                int cdtID = Integer.parseInt(request.getParameter("CdtID"));
                String actUpdate = request.getParameter("Act");
                String timeStartUpdate = request.getParameter("TimeStart");
                String timeEndUpdate = request.getParameter("TimeEnd");
                int curDateIDUpdate = Integer.parseInt(request.getParameter("CurDateID"));
                CurDateAct curDateActUpdate = new CurDateAct(cdtID, actUpdate, timeStartUpdate, timeEndUpdate, curDateIDUpdate);
                dao.updateCurDateAct(curDateActUpdate);
                response.sendRedirect("CurriculumDateControllerURL?service=searchByID&CurID=" + curID);
                
            
        }

        if (service.equals("delete")) {
                                    int curID = Integer.parseInt(request.getParameter("CurID"));

            int cdtID = Integer.parseInt(request.getParameter("CdtID"));
            dao.deleteCurDateAct(cdtID);
                response.sendRedirect("CurriculumDateControllerURL?service=searchByID&CurID=" + curID);
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

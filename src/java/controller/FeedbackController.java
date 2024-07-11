package controller;

import entity.Feedback;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Vector;
import model.DAOFeedback;
import model.DAOTeacher;
import model.DAOUser;

@WebServlet(name = "FeedbackController", urlPatterns = {"/FeedbackControllerURL"})
public class FeedbackController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOFeedback dao = new DAOFeedback();
        DAOUser daoUser = new DAOUser();
        DAOTeacher daoTeacher = new DAOTeacher();
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("listAll")) {
            String userName = (String) session.getAttribute("userName");
            Vector<User> user = daoUser.getAllUsers("SELECT * FROM [User] WHERE Email ='" + userName + "'");
            int teacherID = daoTeacher.getTeacherIDByUserID(user.get(0).getUserID());
            Vector<Feedback> vector = dao.getAllFeedback(teacherID);

            request.setAttribute("data", vector);
            RequestDispatcher dispatcher = request.getRequestDispatcher("FeedbackList.jsp");
            dispatcher.forward(request, response);
        } else if (service.equals("addFeedback")) {
            String dateStr = request.getParameter("date");
            String content = request.getParameter("content");

            String userName = (String) session.getAttribute("userName");
            Vector<User> user = daoUser.getAllUsers("SELECT * FROM [User] WHERE Email ='" + userName + "'");
            int teacherID = daoTeacher.getTeacherIDByUserID(user.get(0).getUserID());

            java.sql.Date date = java.sql.Date.valueOf(dateStr);

            dao.addFeedbackForAllStudents(teacherID, date, content);

            response.sendRedirect("FeedbackControllerURL?service=listAll");
        } else if (service.equals("updateFeedback")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String dateStr = request.getParameter("date");
            String content = request.getParameter("content");

            java.sql.Date date = java.sql.Date.valueOf(dateStr);

            dao.updateFeedback(id, date, content);

            response.sendRedirect("FeedbackControllerURL?service=listAll");
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
}

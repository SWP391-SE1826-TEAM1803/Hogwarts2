package controller;

import entity.ClassCategoryListMenu;

import java.io.IOException;
import java.util.Vector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOClassCategoryMenu;

@WebServlet(name = "ClassCategoryMenuServlet", urlPatterns = {"/ClassCategoryMenuServlet"})
public class ClassCategoryMenuServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOClassCategoryMenu dao = new DAOClassCategoryMenu();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listByDate";
        }

        if (service.equals("listByDate")) {
            String date = request.getParameter("date");
            if (date != null && !date.isEmpty()) {
                Vector<ClassCategoryListMenu> classCategoryMenuList = dao.getClassCategoryMenuByDate(date);
                request.setAttribute("classCategoryMenuList", classCategoryMenuList);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("ListMenuForClass.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
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
        return "ClassCategoryMenuServlet handles displaying menu for classes by date.";
    }
}

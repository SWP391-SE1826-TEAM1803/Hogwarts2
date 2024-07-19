package controller;

import entity.ClassCategory;
import entity.ClassCategoryListMenu;
import entity.ClassCategoryMenu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.DAOClassCategory;
import model.DAOClassCategoryMenu;

@WebServlet("/menuManager")
public class MenuManagerServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String age = request.getParameter("age");
        String meal = request.getParameter("meal");
        String date = request.getParameter("date");
        String[] menuItems = request.getParameterValues("menu");

        DAOClassCategoryMenu daoClassCategoryMenu = new DAOClassCategoryMenu();
        DAOClassCategory daoClassCategory = new DAOClassCategory();

        boolean success = true;
        for (String menuItem : menuItems) {
            ClassCategoryMenu ccm = new ClassCategoryMenu();
            ccm.setCateID(Integer.parseInt(age));
            ccm.setMenuID(Integer.parseInt(menuItem));
            ccm.setDate(date);
            ccm.setMeal(meal);
            int result = daoClassCategoryMenu.insertClassCategoryMenu1(ccm);
            if (result == -1) {
                success = false;
                break;
            }
        }

        if (success) {
            ClassCategory category = daoClassCategory.getClassCategoryByID(Integer.parseInt(age));
            String categoryName = category.getCateName();
            request.setAttribute("successMessage", "Menu added successfully!");
            request.setAttribute("submittedCategoryName", categoryName);
            request.setAttribute("submittedMeal", meal);
            request.setAttribute("submittedDate", date);
            request.setAttribute("submittedMenuItems", menuItems);

            // Fetch today's submitted data
            List<ClassCategoryListMenu> todaySubmittedData = daoClassCategoryMenu.getClassCategoryMenuByDate(date);
            request.setAttribute("todaySubmittedData", todaySubmittedData);
        } else {
            request.setAttribute("errorMessage", "Failed to add menu. It might be duplicate.");
        }

        request.getRequestDispatcher("AddMenuForClass.jsp").forward(request, response);
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

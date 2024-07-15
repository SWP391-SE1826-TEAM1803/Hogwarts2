package controller;

import entity.ClassCategory;
import entity.ClassCategoryMenu;
import entity.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;
import model.DAOClassCategory;
import model.DAOClassCategoryMenu;
import model.DAOMenu;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/menuManager")
public class MenuManagerServlet extends HttpServlet {
    private DAOClassCategory classCategoryDAO;
    private DAOMenu menuDAO;
    private DAOClassCategoryMenu classCategoryMenuDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        classCategoryDAO = new DAOClassCategory();
        menuDAO = new DAOMenu();
        classCategoryMenuDAO = new DAOClassCategoryMenu();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "initialize";
        }
        System.out.println("Action: " + action);

        switch (action) {
            case "initialize":
                initializeMenu(request, response);
                break;
            case "getMenus":
                getMenus(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        submitMenu(request, response);
    }

    private void initializeMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vector<ClassCategory> categories = classCategoryDAO.getAllCategories();
        if (categories == null || categories.isEmpty()) {
            System.out.println("No categories found!");
        } else {
            System.out.println("Categories found: " + categories.size());
            for (ClassCategory category : categories) {
                System.out.println("Category ID: " + category.getCateID() + ", Name: " + category.getCateName());
            }
        }
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/AddMenuForClass.jsp").forward(request, response);
    }

    private void getMenus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cateID = Integer.parseInt(request.getParameter("cateID"));
        System.out.println("Category ID: " + cateID);

        Vector<Menu> menus = classCategoryMenuDAO.getMenusForMeal(cateID, "Breakfast");

        JSONArray menuArray = new JSONArray();
        for (Menu menu : menus) {
            JSONObject menuJson = new JSONObject();
            menuJson.put("menuID", menu.getMenuID());
            menuJson.put("food", menu.getFood());
            menuArray.put(menuJson);
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("menus", menuArray);

        System.out.println("JSON Response: " + jsonResponse.toString());

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }

    private void submitMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cateID = Integer.parseInt(request.getParameter("cateID"));
        int menuID = Integer.parseInt(request.getParameter("menuID"));
        String date = request.getParameter("date");
        System.out.println("Category ID: " + cateID);
        System.out.println("Menu ID: " + menuID);
        System.out.println("Date: " + date);

        classCategoryMenuDAO.addMenuForClass(new ClassCategoryMenu(cateID, menuID, date, "Breakfast"));

        response.sendRedirect("menuManager?action=initialize");
    }
}

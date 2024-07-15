package controller;

import model.DAOClass;
import model.DAOClassCategory;
import entity.Class;
import entity.ClassCategory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertServlet")
public class InsertCategoryClass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insertCategory".equals(action)) {
            String cateName = request.getParameter("cateName");
            DAOClassCategory daoClassCategory = new DAOClassCategory();
            if (daoClassCategory.isCategoryNameExists(cateName)) {
                request.setAttribute("message", "Category name already exists. Please choose a different name.");
                request.getRequestDispatcher("InsertClassCategory.jsp").forward(request, response);
                return;
            }

            ClassCategory classCategory = new ClassCategory();
            classCategory.setCateName(cateName);
            int cateID = daoClassCategory.insertCategory(classCategory);

            if (cateID != -1) {
                request.setAttribute("message", "Category inserted successfully with ID: " + cateID);
                request.setAttribute("cateID", cateID);
                request.getRequestDispatcher("InsertClassCategory.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Error inserting category.");
                request.getRequestDispatcher("InsertClassCategory.jsp").forward(request, response);
            }
        } else if ("insertClass".equals(action)) {
            String className = request.getParameter("className");
            int cateID = Integer.parseInt(request.getParameter("cateID"));

            DAOClass daoClass = new DAOClass();
            if (daoClass.isClassNameExists(className)) {
                request.setAttribute("message", "Class name already exists. Please choose a different name.");
                request.getRequestDispatcher("InsertClassCategory.jsp").forward(request, response);
                return;
            }

            Class classObj = new Class();
            classObj.setClassName(className);
            classObj.setCateID(cateID);
            boolean result = daoClass.insertClass1(classObj);

            if (result) {
                request.setAttribute("message", "Class inserted successfully!");
            } else {
                request.setAttribute("message", "Error inserting class.");
            }
            request.getRequestDispatcher("InsertClassCategory.jsp").forward(request, response);
        } else if ("checkCategory".equals(action)) {
            String cateName = request.getParameter("cateName");
            DAOClassCategory daoClassCategory = new DAOClassCategory();
            boolean exists = daoClassCategory.isCategoryNameExists(cateName);
            response.getWriter().write(String.valueOf(exists));
        } else if ("checkClass".equals(action)) {
            String className = request.getParameter("className");
            DAOClass daoClass = new DAOClass();
            boolean exists = daoClass.isClassNameExists(className);
            response.getWriter().write(String.valueOf(exists));
        }
    }
}

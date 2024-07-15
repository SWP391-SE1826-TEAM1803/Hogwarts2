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

            ClassCategory classCategory = new ClassCategory();
            classCategory.setCateName(cateName);

            DAOClassCategory daoClassCategory = new DAOClassCategory();
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

            Class classObj = new Class();
            classObj.setClassName(className);
            classObj.setCateID(cateID);

            DAOClass daoClass = new DAOClass();
            boolean result = daoClass.insertClass1(classObj);

            if (result) {
                request.setAttribute("message", "Class inserted successfully!");
            } else {
                request.setAttribute("message", "Error inserting class.");
            }
            request.getRequestDispatcher("InsertClassCategory.jsp").forward(request, response);
        }
    }
}

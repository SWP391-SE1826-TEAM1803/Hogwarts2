package controller;

import model.DAOClassCategory;
import entity.ClassCategory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOClassCategory dao = new DAOClassCategory();
        Vector<ClassCategory> categories = dao.getAllCategories();
        
<<<<<<< HEAD
        request.setAttribute("Categories", categories);
        request.getRequestDispatcher("Categories.jsp").forward(request, response);
=======
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("categories.jsp").forward(request, response);
>>>>>>> 934c762eb3fa13cf0cf25226b466c4e8fb19da2d
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 934c762eb3fa13cf0cf25226b466c4e8fb19da2d

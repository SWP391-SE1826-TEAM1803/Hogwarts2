import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.ClassCategory;

import java.io.IOException;
import java.util.Vector;

@WebServlet("/ClassCategoryServlet")
public class ClassCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vector<ClassCategory> categories = new Vector<>();

        // Giả lập dữ liệu danh mục lớp học
        categories.add(new ClassCategory(1, "Potions"));
        categories.add(new ClassCategory(2, "Defense Against the Dark Arts"));
        categories.add(new ClassCategory(3, "Herbology"));
        categories.add(new ClassCategory(4, "Transfiguration"));

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("classCategories.jsp").forward(request, response);
    }
}

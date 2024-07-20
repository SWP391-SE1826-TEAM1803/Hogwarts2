package controller;
import entity.Class;
import entity.Teacher;
import entity.TeacherAdd;
import entity.TeacherInfo;
import entity.User;
import entity.UserAdd;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.Vector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOClass;
import model.DAOTeacher;
import model.DAOUser;

@WebServlet(name = "TeacherController", urlPatterns = {"/TeacherControllerURL"})
public class TeacherController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOTeacher dao = new DAOTeacher();
        DAOClass daoClass = new DAOClass();
        DAOUser daoUser = new DAOUser();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("addUserAndTeacher")) {
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String degree = request.getParameter("degree");

            // Create user object
            UserAdd user = new UserAdd(fullName, gender, address, phone, email, "Teacher", password);
            int userID = daoUser.insertUser2(user);

            if (userID > 0) {
                // Create teacher object
                TeacherAdd teacher = new TeacherAdd(String.valueOf(userID), degree);
                int teacherID = dao.insertTeacher(teacher);

                if (teacherID > 0) {
                    response.sendRedirect("TeacherControllerURL?service=listAll");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else if (userID == -1) {
                // Duplicate email error handling
                request.setAttribute("errorMessage", "Email already exists.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("AddUserAndTeacher.jsp");
                dispatcher.forward(request, response);
            } else if (userID == -2) {
                // Duplicate phone error handling
                request.setAttribute("errorMessage", "Phone number already exists.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("AddUserAndTeacher.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } else if (service.equals("listAll")) {
            Vector<TeacherInfo> vector = dao.getTeacherInfo();
            request.setAttribute("data", vector);

            RequestDispatcher dispatcher = request.getRequestDispatcher("TeacherManager.jsp");
            dispatcher.forward(request, response);
        } else if (service.equals("update")) {
            update(request, response, dao, daoClass);
        } else if (service.equals("delete")) {
            delete(request, response, dao);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response, DAOTeacher dao, DAOClass daoClass)
        throws ServletException, IOException {
    String submit = request.getParameter("submit");
    if (submit == null) {
        String teacherID = request.getParameter("TeacherID");
        Vector<Teacher> vector = dao.getAllTeachers("SELECT * FROM Teacher WHERE TeacherID='" + teacherID + "'");
        request.setAttribute("vector", vector);

        // Lấy danh sách lớp từ DAOClass với tên danh mục
        Vector<Class> classes = daoClass.getAllClassesWithCategory();
        request.setAttribute("classes", classes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateTeacher.jsp");
        dispatcher.forward(request, response);
    } else {
        String teacherID = request.getParameter("TeacherID");
        String className = request.getParameter("ClassName");

        // Gọi phương thức DAO để cập nhật lớp của giáo viên
        boolean updated = dao.updateTeacherClass(Integer.parseInt(teacherID), className);

        if (updated) {
            // Chuyển hướng về danh sách sau khi cập nhật
            response.sendRedirect("TeacherControllerURL?service=listAll");
        } else {
            // Xử lý nếu cập nhật không thành công
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}


    private void delete(HttpServletRequest request, HttpServletResponse response, DAOTeacher dao)
            throws ServletException, IOException {
        String teacherID = request.getParameter("TeacherID");
        String className = request.getParameter("ClassName");
        dao.deleteTeacherFromClass(Integer.parseInt(teacherID), className);

        response.sendRedirect("TeacherControllerURL?service=listAll");
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
        return "TeacherController handles adding, listing, updating, and deleting teachers.";
    }
}

package controller;

import entity.Class;
import entity.StudentClass;
import java.io.IOException;
import java.util.Vector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOClass;

@WebServlet(name = "ClassController", urlPatterns = {"/ClassControllerURL"})
public class ClassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        DAOClass dao = new DAOClass();

        switch (service) {
            case "listAll":
                listAllClasses(request, response, dao);
                break;
            case "updateClass":
                updateClass(request, response, dao);
                break;
            case "addClass":
                addClass(request, response, dao);
                break;
            case "deleteClass":
                deleteClass(request, response, dao);
                break;
            case "detailClass":
                detailClass(request, response, dao);
                break;
            default:
                listAllClasses(request, response, dao);
                break;
        }
    }

    private void listAllClasses(HttpServletRequest request, HttpServletResponse response, DAOClass dao)
            throws ServletException, IOException {
        Vector<Class> classList = dao.getAllClassesWithCategory();
        request.setAttribute("classList", classList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClassList.jsp");
        dispatcher.forward(request, response);
    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response, DAOClass dao)
            throws ServletException, IOException {
        String submit = request.getParameter("submit");
        if (submit == null) {
            int classID = Integer.parseInt(request.getParameter("classID"));
            Class cls = dao.getClassByID(classID);
            request.setAttribute("class", cls);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateClass.jsp");
            dispatcher.forward(request, response);
        } else {
            int classID = Integer.parseInt(request.getParameter("classID"));
            String className = request.getParameter("className");
            int cateID = Integer.parseInt(request.getParameter("cateID"));

            Class cls = new Class(classID, className, cateID);
            int result = dao.updateClass(cls);
            request.setAttribute("message", result > 0 ? "Class updated successfully." : "Failed to update class.");

            listAllClasses(request, response, dao);
        }
    }

    private void addClass(HttpServletRequest request, HttpServletResponse response, DAOClass dao)
            throws ServletException, IOException {
        String className = request.getParameter("className");
        int cateID = Integer.parseInt(request.getParameter("cateID"));

        Class cls = new Class(className, cateID);
        int result = dao.insertClass(cls);
        request.setAttribute("message", result > 0 ? "Class added successfully." : "Failed to add class.");

        listAllClasses(request, response, dao);
    }

    private void deleteClass(HttpServletRequest request, HttpServletResponse response, DAOClass dao)
            throws ServletException, IOException {
        int classID = Integer.parseInt(request.getParameter("classID"));
        int result = dao.deleteClass(classID);
        request.setAttribute("message", result > 0 ? "Class deleted successfully." : "Failed to delete class.");

        listAllClasses(request, response, dao);
    }
    
    private void detailClass(HttpServletRequest request, HttpServletResponse response, DAOClass dao)
            throws ServletException, IOException {
        int classID = Integer.parseInt(request.getParameter("classID"));
        Vector<StudentClass> studentList = dao.getStudentsByClassForLatestSchoolYear(classID);
        request.setAttribute("studentList", studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClassDetail.jsp");
        dispatcher.forward(request, response);
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
        return "Class Controller";
    }
    
    
}

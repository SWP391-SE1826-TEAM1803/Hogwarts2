package controller;

import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOUser;

@WebServlet(name = "UserController", urlPatterns = {"/UserControllerURL"})
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOUser dao = new DAOUser();
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        switch (service) {
            case "addUser":
                // Get data from request
                String FullName = request.getParameter("FullName");
                String Gender = request.getParameter("Gender");
                String Address = request.getParameter("Address");
                String Phone = request.getParameter("Phone");
                String Email = request.getParameter("Email");
                String Role = request.getParameter("Role");
                String Password = request.getParameter("Password");
                // Create User object
                User user = new User(0, FullName, Gender, Address, Phone, Email, Role, Password);
                // Insert User
                dao.insertUser(user);
                // Redirect to listAll
                response.sendRedirect("UserControllerURL?service=listAll");
                break;

            case "listAll":
                // Get role parameter
                String role = request.getParameter("role");
                Vector<User> vector;

                if (role == null || role.isEmpty()) {
                    // Get all users
                    vector = dao.getAllUsers("SELECT * FROM [User]");
                } else {
                    // Get users by role
                    vector = dao.getAllUsers("SELECT * FROM [User] WHERE role = '" + role + "'");
                }

                // Set data to request
                request.setAttribute("data", vector);
                // Forward to JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("ManageUser.jsp");
                dispatcher.forward(request, response);
                break;

            case "updateUser":
                String submit = request.getParameter("submit");
                if (submit == null) {
                    // Show update form
                    int userID = Integer.parseInt(request.getParameter("UserID"));
                    User userToUpdate = dao.getUserByID(userID);
                    request.setAttribute("user", userToUpdate);
                    RequestDispatcher updateDispatcher = request.getRequestDispatcher("ManageUser.jsp?service=updateUserForm");
                    updateDispatcher.forward(request, response);
                } else {
                    // Update user data
                    int UserID = Integer.parseInt(request.getParameter("UserID"));
                    FullName = request.getParameter("FullName");
                    Gender = request.getParameter("Gender");
                    Address = request.getParameter("Address");
                    Phone = request.getParameter("Phone");
                    Email = request.getParameter("Email");
                    Role = request.getParameter("Role");
                    Password = request.getParameter("Password");
                    User updatedUser = new User(UserID, FullName, Gender, Address, Phone, Email, Role, Password);
                    dao.updateUser(updatedUser);
                    response.sendRedirect("UserControllerURL?service=listAll");
                }
                break;

            case "deleteUser":
                String userID = request.getParameter("UserID");
                dao.deleteUser(userID);
                response.sendRedirect("UserControllerURL?service=listAll");
                break;
                
            case "addUserForm":
                RequestDispatcher addDispatcher = request.getRequestDispatcher("ManageUser.jsp?service=addUserForm");
                addDispatcher.forward(request, response);
                break;

            case "updateUserForm":
                int userIDToUpdate =Integer.parseInt(request.getParameter("UserID"));
                User userToUpdateForm = dao.getUserByID(userIDToUpdate);
                request.setAttribute("user", userToUpdateForm);
                RequestDispatcher updateFormDispatcher = request.getRequestDispatcher("ManageUser.jsp?service=updateUserForm");
                updateFormDispatcher.forward(request, response);
                break;

            default:
                response.sendRedirect("UserControllerURL?service=listAll");
                break;
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
        return "Short description";
    }
}

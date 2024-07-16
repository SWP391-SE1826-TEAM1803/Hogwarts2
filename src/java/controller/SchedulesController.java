/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CurDateAct;
import entity.Schedules;
import entity.CurriculumDate;
import entity.User;
import model.DAOSchedules;
import model.DAOCurriculumDate;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCurDateAct;
import model.DAOSchoolYearClass;
import model.DAOTeacher;
import model.DAOUser;

@WebServlet(name = "SchedulesController", urlPatterns = {"/SchedulesControllerURL"})
public class SchedulesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        DAOUser daoUser = new DAOUser();
        DAOTeacher daoTeacher = new DAOTeacher();
        DAOCurDateAct daoAct = new DAOCurDateAct();
        DAOSchedules dao = new DAOSchedules();
        DAOCurriculumDate daoCurriculumDate = new DAOCurriculumDate();
        DAOSchoolYearClass daoSchoolYearClass = new DAOSchoolYearClass();
        RequestDispatcher dispatcher;
        String userName = (String) session.getAttribute("userName");
        Vector<User> user = daoUser.getAllUsers("SELECT * FROM [User] WHERE Email ='" + userName + "'");
        int teacherID = daoTeacher.getTeacherIDByUserID(user.get(0).getUserID());

        switch (service) {
            case "listAll":

                Vector<Schedules> schedulesList = dao.getAllSchedulesBySycIDAndTeacherID(teacherID);
                request.setAttribute("schedulesList", schedulesList);
                dispatcher = request.getRequestDispatcher("Schedules.jsp");
                dispatcher.forward(request, response);
                break;

            case "addSchedule":

                Vector<CurriculumDate> curriculumDates = daoCurriculumDate.getAllCurriculumDatesWithCurName(teacherID);
                request.setAttribute("curriculumDates", curriculumDates);
                dispatcher = request.getRequestDispatcher("AddSchedules.jsp");
                dispatcher.forward(request, response);
                break;

            case "insertSchedule":
                String date = request.getParameter("date");
                int curDateID = Integer.parseInt(request.getParameter("curDateID"));
                int syC_ID = daoSchoolYearClass.getSycIDByTeacherID(teacherID);

                Schedules schedule = new Schedules(0, date, curDateID, syC_ID);
                dao.insertSchedule(schedule);
                response.sendRedirect("SchedulesControllerURL?service=listAll");
                break;
                
//            case "search":
//                String searchDateNumber = request.getParameter("searchDateNumber");
//                Vector<CurDateAct> searchResults = daoAct.searchCurDateActByDateNumber(teacherID, searchDateNumber);
//                request.setAttribute("curriculumDates", searchResults);
//                dispatcher = request.getRequestDispatcher("AddSchedules.jsp");
//                dispatcher.forward(request, response);
//                break;
                
            case "search":
                String searchDateNumber = request.getParameter("searchDateNumber");
                Vector<CurriculumDate> searchResults = daoCurriculumDate.searchCurriculumDatesByDateNumber(teacherID, searchDateNumber);
                request.setAttribute("curriculumDates", searchResults);
                dispatcher = request.getRequestDispatcher("AddSchedules.jsp");
                dispatcher.forward(request, response);
            break;

            case "editSchedule":
                int schedulesID = Integer.parseInt(request.getParameter("schedulesID"));
                Schedules existingSchedule = dao.getScheduleByID(schedulesID);
                Vector<CurriculumDate> allCurriculumDates = daoCurriculumDate.getAllCurriculumDatesWithCurName(teacherID);
                request.setAttribute("schedule", existingSchedule);
                request.setAttribute("curriculumDates", allCurriculumDates);
                dispatcher = request.getRequestDispatcher("EditSchedules.jsp");
                dispatcher.forward(request, response);
                break;
                
            case "updateSchedule":
                int schedulesIDToUpdate = Integer.parseInt(request.getParameter("schedulesID"));
                String updatedDate = request.getParameter("date");
                int updatedCurDateID = Integer.parseInt(request.getParameter("curDateID"));
                int updatedSyC_ID = daoSchoolYearClass.getSycIDByTeacherID(teacherID);

                Schedules updatedSchedule = new Schedules(schedulesIDToUpdate, updatedDate, updatedCurDateID, updatedSyC_ID);
                dao.updateSchedule(updatedSchedule);
                response.sendRedirect("SchedulesControllerURL?service=listAll");
                break;
                
            case "deleteSchedule":
                int scheduleIDToDelete = Integer.parseInt(request.getParameter("schedulesID"));
                dao.deleteSchedule(scheduleIDToDelete);
                response.sendRedirect("SchedulesControllerURL?service=listAll");
                break;
                
            default:
                response.sendRedirect("SchedulesControllerURL?service=listAll");
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
        return "Schedules Controller";
    }
}

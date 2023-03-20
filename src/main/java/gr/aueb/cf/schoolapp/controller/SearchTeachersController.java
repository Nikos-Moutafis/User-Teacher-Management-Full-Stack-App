package gr.aueb.cf.schoolapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

@WebServlet(name = "SearchTeachersController", value = "/schoolapp/search")
public class SearchTeachersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String lastname = request.getParameter("lastname").trim();
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setLastname(lastname);
        String message = "";
        try {
            List<Teacher> teachers = teacherService.getTeachersByLastname(teacherDTO.getLastname());
            if (teachers.size() == 0) {
                request.setAttribute("teachersNotFound", true);
                request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
                        .forward(request, response);
            }
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/schoolapp/static/templates/teachers.jsp").forward(request, response);
        } catch (TeacherDAOException e) {
            message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/schoolapp/teachersmenu.jsp").forward(request, response);
        }
    }
}

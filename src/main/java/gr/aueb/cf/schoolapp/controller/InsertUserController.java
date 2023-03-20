package gr.aueb.cf.schoolapp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.validation.Validator;

@WebServlet("/schoolapp/insertUser")
public class InsertUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("error", "");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        request.setAttribute("insertedUser", userDTO);
        try {
            if (userService.isEmailTaken(userDTO.getUsername())){
                request.setAttribute("error", "username already taken");
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
                return;
            }

            String error = Validator.validateUser(userDTO);
            if (!error.equals("")) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }
            userService.insertUser(userDTO);
            request.getRequestDispatcher("/schoolapp/static/templates/userinserted.jsp")
                    .forward(request, response);
        } catch (UserDAOException e) {
            //e.printStackTrace();
            request.setAttribute("sqlError", true);
            request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                    .forward(request, response);
        }
    }
}


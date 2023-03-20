package gr.aueb.cf.schoolapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

@WebServlet(name = "SearchUsersController", value = "/schoolapp/searchUser")
public class SearchUsersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserDAO userDAO = new UserDAOImpl();
    IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String username = request.getParameter("username").trim();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        String message = "";
        try {
            List<User> users = userService.getUserByUsername(userDTO.getUsername());
            if (users.size() == 0) {
                request.setAttribute("usersNotFound", true);
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }
            request.setAttribute("users", users);
            request.getRequestDispatcher("/schoolapp/static/templates/users.jsp").forward(request, response);
        } catch (UserDAOException e) {
            message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/schoolapp/usersmenu.jsp").forward(request, response);
        }
    }
}

package gr.aueb.cf.schoolapp.controller;



import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapp/deleteuser")
public class DeleteUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserDAO userDAO = new UserDAOImpl();
    IUserService userService = new UserServiceImpl(userDAO);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id").trim());
        String username = request.getParameter("username").trim();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        try {
            userService.deleteUser(id);
            request.setAttribute("user", userDTO);
            request.getRequestDispatcher("/schoolapp/static/templates/userdeleted.jsp")
                    .forward(request, response);
        } catch (UserNotFoundException | UserDAOException e) {
            request.setAttribute("deleteAPIError", true);
            request.getRequestDispatcher("/schoolapp/static/templates/users.jsp")
                    .forward(request, response);
        }
    }

}

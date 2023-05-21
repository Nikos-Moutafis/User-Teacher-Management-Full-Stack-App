package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(email);
        userDTO.setPassword(password);

        User user = null;
        try {
            user = AuthenticationProvider.authenticate(userDTO);
        } catch (UserDAOException e) {
            throw new RuntimeException(e);
        }

        /*
         *If user is not authenticated then authentication provider
         * returns null, then redirect user to login
         */
        if (user == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        /*
         * If user is authenticated(not null), create a new session
         * Set a max active period of 15 minutes to session
         *  Create a new cookie  and assign to it an id
         * and max age(equal to session max active period)
         * Add cookie to the response
         * and then redirect user to menu
         * depending on his role (admin or user)
         */
        HttpSession session = request.getSession(true);
        session.setAttribute("username",user.getUsername());

        session.setMaxInactiveInterval(60 * 15);

        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(session.getMaxInactiveInterval());
        response.addCookie(cookie);

        if (user.getUsername().equals("admin")){
            response.sendRedirect(request.getContextPath() + "/schoolapp/adminchoice");
        }else {
            response.sendRedirect(request.getContextPath() +  "/schoolapp/menu");
        }

    }
}

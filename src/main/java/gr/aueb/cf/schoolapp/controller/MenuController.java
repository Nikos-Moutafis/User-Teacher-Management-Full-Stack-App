package gr.aueb.cf.schoolapp.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/schoolapp/menu")
public class MenuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
                .forward(request, response);
    }
}

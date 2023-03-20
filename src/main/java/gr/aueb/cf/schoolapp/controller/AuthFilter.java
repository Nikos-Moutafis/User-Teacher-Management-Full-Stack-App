package gr.aueb.cf.schoolapp.controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //Check for session cookie
        Cookie[] cookies = req.getCookies();
        boolean authenticated = false;

        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("JSESSIONID")){
                    HttpSession session = req.getSession(false);
                    if ((session != null) && (session.getId().equals(cookie.getValue()))){
                        //session is valid, user is authenticated
                        authenticated = true;
                    }else {
                        String modifiedCookie = cookie.getValue().substring(0, cookie.getValue().length() -6);
                        assert session != null;
                        if (session.getId().equals(modifiedCookie)) authenticated = true;
                    }
                }
            }
        }

        if (authenticated){
            chain.doFilter(request, response);
        }else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}

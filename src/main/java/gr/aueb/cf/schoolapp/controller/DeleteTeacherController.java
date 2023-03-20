package gr.aueb.cf.schoolapp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;


@WebServlet("/schoolapp/delete")
public class DeleteTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ITeacherDAO teacherDAO = new TeacherDAOImpl();
	ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id").trim());	
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(id);
		teacherDTO.setFirstname(firstname);
		teacherDTO.setLastname(lastname);
		try {
			teacherServ.deleteTeacher(id);
			request.setAttribute("teacher", teacherDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/teacherdeleted.jsp")
					.forward(request, response);
		} catch (TeacherNotFoundException | TeacherDAOException e) {
			request.setAttribute("deleteAPIError", true);
			request.getRequestDispatcher("/schoolapp/static/templates/teachers.jsp")
					.forward(request, response);
		}
	}
}


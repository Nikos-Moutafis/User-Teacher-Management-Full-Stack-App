package gr.aueb.cf.schoolapp.controller;

import java.io.IOException;

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
import gr.aueb.cf.schoolapp.validation.Validator;

@WebServlet("/schoolapp/update")
public class UpdateTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schoolapp/static/templates/teacherupdate.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		TeacherDTO newTeacherDTO = new TeacherDTO();
		newTeacherDTO.setId(id);
		newTeacherDTO.setFirstname(firstname);
		newTeacherDTO.setLastname(lastname);
		request.setAttribute("insertedTeacher", newTeacherDTO);
		
		try {
			String error = Validator.validate(newTeacherDTO);
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
						.forward(request, response);
			}
			teacherServ.updateTeacher(newTeacherDTO);
			request.setAttribute("teacher", newTeacherDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/teacherupdated.jsp")
					.forward(request, response);
		} catch (TeacherNotFoundException | TeacherDAOException e) {
			String message = e.getMessage();
			request.setAttribute("isError", true);
			request.setAttribute("teacher", newTeacherDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/teacherupdated.jsp")
					.forward(request, response);

		}
	}
}

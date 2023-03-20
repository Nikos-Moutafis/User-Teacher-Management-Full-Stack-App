package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDAOException {
        if (teacherToInsert == null) return null;

        try {
            Teacher teacher = mapTeacher(teacherToInsert);
            return teacherDAO.insert(teacher);

        } catch (TeacherDAOException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherDTO teacherToUpdate)
            throws TeacherDAOException, TeacherNotFoundException {

        if (teacherToUpdate == null) return null;

        try {
            if (teacherDAO.getById(teacherToUpdate.getId()) == null) {
                throw new TeacherNotFoundException("Teacher with id " + teacherToUpdate.getId()
                        + " was not found");
            }

            Teacher teacher = mapTeacher(teacherToUpdate);
            return teacherDAO.update(teacher);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException {
        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }
            teacherDAO.delete(id);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname)
            throws TeacherDAOException {
        List<Teacher> teachers = new ArrayList<>();
        if (lastname == null) return teachers;

        try {
            teachers = teacherDAO.getByLastname(lastname);
            return teachers;
        } catch (TeacherDAOException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    private Teacher mapTeacher(TeacherDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}

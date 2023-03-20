package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            p.setString(1, firstname);
            p.setString(2, lastname);
            p.executeUpdate();
            return teacher;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace(); // logging
            throw new TeacherDAOException("SQL Error in Teacher " + teacher + " insertion");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            p.setString(1, firstname);
            p.setString(2, lastname);
            p.setInt(3, id);
            p.executeUpdate();
            return teacher;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher " + teacher.getLastname()
                    + " update");
        }
    }

    @Override
    public void delete(int id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher with id = "
                    + id  + " deleted");
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        System.out.println("DAO " + lastname);
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
        ResultSet rs;
        List<Teacher> teachers = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, lastname + '%');
            rs = p.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getInt("ID"),rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher with lastname = " + lastname);
        }
    }

    @Override
    public Teacher getById(int id) throws TeacherDAOException {
        Teacher teacher = null;
        ResultSet rs;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, id);
            rs = p.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"),rs.getString("LASTNAME"));
            }
            return teacher;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher with id = " + id);
        }
    }
}

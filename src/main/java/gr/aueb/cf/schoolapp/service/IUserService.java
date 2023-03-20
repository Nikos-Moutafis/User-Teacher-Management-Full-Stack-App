package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {

    User insertUser(UserDTO userToInsert)throws UserDAOException;
    User updateUser(UserDTO userUpdate) throws UserDAOException, UserNotFoundException;
    void deleteUser(int id)throws UserDAOException,UserNotFoundException;
    List<User>getUserByUsername(String username)throws UserDAOException;

    boolean isEmailTaken(String username) ;
}

package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService{

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO){
        this.userDAO = userDAO;
    }



    @Override
    public User insertUser(UserDTO userToInsert) throws UserDAOException {
        if (userToInsert == null) return  null;

        try {
            User user = mapUser(userToInsert);
            return userDAO.insert(user);
        }catch (UserDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User updateUser(UserDTO userUpdate) throws UserDAOException, UserNotFoundException {
        if (userUpdate == null) return  null;

        try {
            User user = mapUser(userUpdate);
            return userDAO.update(user);
        }catch (UserDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUser(int id) throws UserDAOException, UserNotFoundException {
        try {
            if (userDAO.getById(id) == null){
                throw new UserNotFoundException("User with id " + id + " not found");
            }
            userDAO.delete(id);
        }catch (UserDAOException | UserNotFoundException e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public List<User> getUserByUsername(String username) throws UserDAOException {
        List<User> users = new ArrayList<>();
        if (username == null) return users;

        try {
            users = userDAO.getByUsername(username);
            return users;
        }catch (UserDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean isEmailTaken(String username){
        if (username == null) return false;

        try {
            List<User> userList = userDAO.getAll();
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
        }catch (SQLException | UserDAOException | ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }



    private User mapUser(UserDTO dto){
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }
}

package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;

public class AuthenticationProvider {
    private static final IUserDAO userDao = new UserDAOImpl();
    private AuthenticationProvider(){}

    public static User authenticate(UserDTO userDTO) throws UserDAOException {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        User user = new User();

        if (username.equals("admin@gmail.com") && (password.equals(System.getenv("TS_ADMIN_PASSWORD")))){
            user.setUsername("admin");
            return user;
        } else if (username.equals("admin@gmail.com") && (!password.equals(System.getenv("TS_ADMIN_PASSWORD"))))  {
            return null;
        } else if (!userDao.isUserValid(userDTO.getUsername(), userDTO.getPassword())){
            return null;
        }

        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
    }

}

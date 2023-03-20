package gr.aueb.cf.schoolapp.validation;

import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.UserDTO;

public class Validator {

    private Validator(){}

    public static String validate(TeacherDTO dto){
        if (dto.getFirstname().equals("")){
            return "Firstname: Empty";
        }

        if ((dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32)){
            return "Firstname: Length";
        }

        if (dto.getLastname().equals("")){
            return "Lastname: Empty";
        }

        if ((dto.getLastname().length() < 3 || dto.getLastname().length() > 32)){
            return "Lastname: Length";
        }


        return "";
    }


    public static String validateUser(UserDTO userDTO){
        if (userDTO.getUsername().equals("")){
            return "Username: Empty";
        }

        if ((userDTO.getUsername().length() < 3 || userDTO.getUsername().length() > 32)){
            return "Username: Length";
        }

        if (userDTO.getPassword().equals("")){
            return "Password: Empty";
        }

        if ((userDTO.getPassword().length() < 3)){
            return "Lastname: Length";
        }


        return "";
    }
}

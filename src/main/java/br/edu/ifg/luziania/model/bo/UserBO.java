package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.UserDAO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import br.edu.ifg.luziania.model.entity.Users;
import br.edu.ifg.luziania.model.util.Session;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class UserBO {
    @Inject
    UserDAO userDAO;

    @Inject
    Session session;
    
    public AuthReturnDTO authenticate(String email, String password) {
        Users users = userDAO.getByEmailAndPassword(email, password);
        
        if (users.getName().isEmpty())
            return new AuthReturnDTO("/login", "Invalid Credentials", false);

        session.setName(users.getName());

        return new AuthReturnDTO("/main", "Hello " + users.getName() + "!", true);

    }

    @Transactional
    public UserReturnDTO save(UserDTO userDTO) {
        Users entity = new Users(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

        try {
            userDAO.save(entity);

            return new UserReturnDTO(200, "/login", "Successfully registered!");

        } catch (Exception exception) {
            return new UserReturnDTO(500, "/register", "An error has occurred when registering");
        }
    }
}

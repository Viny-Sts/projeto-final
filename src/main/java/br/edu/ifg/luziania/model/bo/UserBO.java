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

import static java.util.Objects.isNull;

@Dependent
public class UserBO {
    @Inject
    UserDAO userDAO;

    @Inject
    Session session;
    
    public AuthReturnDTO authenticate(String email, String password) {
        Users users = userDAO.getByEmailAndPassword(email, password);
        AuthReturnDTO authReturnDTO = new AuthReturnDTO();
        
        if (isNull(users)) {
            authReturnDTO.setUrl("/");
            authReturnDTO.setMessage("Invalid Credentials");

            authReturnDTO.setAuth(false);

        } else {
            authReturnDTO.setUrl("/main");
            authReturnDTO.setMessage("Hello " + users.getName() + "!");

            authReturnDTO.setAuth(true);

            session.setName(users.getName());
        }

        return authReturnDTO;
    }

    @Transactional
    public UserReturnDTO save(UserDTO userDTO) {
        UserReturnDTO userReturnDTO = new UserReturnDTO();

        Users entity = new Users(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

        try {
            userDAO.save(entity);

            userReturnDTO.setStatus(200);
            userReturnDTO.setUrl("/login");
            userReturnDTO.setMessage("Successfully registered!");

        } catch (Exception exception) {
            userReturnDTO.setStatus(500);
            userReturnDTO.setUrl("/register");
            userReturnDTO.setMessage("An error has occurred when registering");
        }

        return userReturnDTO;
    }
}

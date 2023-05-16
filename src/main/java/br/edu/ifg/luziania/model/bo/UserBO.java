package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.UserDAO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static java.util.Objects.isNull;

@Dependent
public class UserBO {
    @Inject
    UserDAO userDAO;
    
    public AuthReturnDTO autenticar(String email, String password, Integer id) {
        AuthReturnDTO authReturnDTO = new AuthReturnDTO();

        User user = userDAO.getById(email, password, id);
        
        if (isNull(user)) {
            //authReturnDTO.setUrl
            authReturnDTO.setAuth(false);

        } else {
            authReturnDTO.setAuth(true);
        }
    }

    public UserDTO save(UserDTO userDTO) {
        User entity = new User();

        entity.setName(userDTO.getName());
        entity.setPassword(userDTO.getPassword());

        try {
            userDAO.save(entity);
            return userDTO;

        } catch (Exception exception) {
            return null;
        }
    }
}

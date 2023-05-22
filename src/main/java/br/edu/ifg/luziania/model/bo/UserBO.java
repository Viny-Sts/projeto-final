package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.UserDAO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import br.edu.ifg.luziania.model.entity.User;
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
        User user = userDAO.getByEmailAndPassword(email, password);
        AuthReturnDTO authReturnDTO = new AuthReturnDTO();
        
        if (isNull(user)) {
            authReturnDTO.setUrl("/");
            authReturnDTO.setAuth(false);
            authReturnDTO.setMessage("Invalid Credentials");

        } else {
            authReturnDTO.setUrl("/principal");
            authReturnDTO.setAuth(true);
            authReturnDTO.setMessage("Hello "+user.getName()+"!");
            session.setName(user.getName());
        }

        return authReturnDTO;
    }

    @Transactional
    public UserReturnDTO save(UserDTO userDTO) {
        UserReturnDTO userReturnDTO = new UserReturnDTO();
        User entity = new User();

        entity.setName(userDTO.getName());
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(userDTO.getPassword());

        try {
            userDAO.save(entity);

            userReturnDTO.setStatus(200);
            userReturnDTO.setMessage("Usuário salvo com sucesso!");
            userReturnDTO.setUrl("/");

        } catch (Exception exception) {
            userReturnDTO.setStatus(500);
            userReturnDTO.setMessage("Falha ao salvar usuário!");
            userReturnDTO.setUrl("/usuario");
        }

        return userReturnDTO;
    }
}

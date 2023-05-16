package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.UserDAO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import br.edu.ifg.luziania.model.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static java.util.Objects.isNull;

@Dependent
public class UserBO {
    @Inject
    UserDAO userDAO;
    
    public AuthReturnDTO autenticar(String ) {
        AuthReturnDTO authReturnDTO = new AuthReturnDTO();

        User user = userDAO.getById();
        
        if (isNull(user)) {
            //authReturnDTO.setUrl
            authReturnDTO.setAuth(false);

        } else {
            authReturnDTO.setAuth(true);
        }
    }
}

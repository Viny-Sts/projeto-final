package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ProfileDAO;
import br.edu.ifg.luziania.model.dao.UserDAO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import br.edu.ifg.luziania.model.entity.Profiles;
import br.edu.ifg.luziania.model.entity.Users;
import br.edu.ifg.luziania.model.util.Session;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class UserBO {
    @Inject
    UserDAO userDAO;
    @Inject
    ProfileDAO profileDAO;
    @Inject
    Session session;
    
    public AuthReturnDTO authenticate(String email, String password) {
        if (userDAO.getByEmailAndPassword(email, password) == null)
            return new AuthReturnDTO("/login", "Invalid Credentials", false);

        Users users = userDAO.getByEmailAndPassword(email, password);
        Profiles profiles = profileDAO.getByName(users.getProfile());
        
        session.setName(users.getProfile());

        List<Boolean> permissions = new ArrayList<>();

        permissions.add(profiles.getMainAccess());
        permissions.add(profiles.getActivityAccess());
        permissions.add(profiles.getUserManagement());
        permissions.add(profiles.getProfileManagement());

        session.setPermissions(permissions);

        return new AuthReturnDTO("/main", "Hello " + users.getName() + "!", true);
    }

    public UserReturnDTO list() {
        if (userDAO.getAllUsers() == null)
            return new UserReturnDTO(500, "/admin", "There's no account registered");

        return new UserReturnDTO(200, "/admin", "Users: " + userDAO.getAllUsers() + ";");
    }

    @Transactional
    public UserReturnDTO save(UserDTO userDTO) {
        try {
            Users user = new Users(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getProfile());
            userDAO.save(user);

            return new UserReturnDTO(200, "/login", "Successfully registered!");

        } catch (Exception exception) {
            return new UserReturnDTO(500, "/register", "An error has occurred when registering");
        }
    }
}

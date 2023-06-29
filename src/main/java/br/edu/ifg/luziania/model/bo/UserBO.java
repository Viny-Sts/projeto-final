package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ProfileDAO;
import br.edu.ifg.luziania.model.dao.UserDAO;
import br.edu.ifg.luziania.model.dto.ActivityDTO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import br.edu.ifg.luziania.model.entity.Profiles;
import br.edu.ifg.luziania.model.entity.Users;
import br.edu.ifg.luziania.model.util.Session;

import javax.servlet.http.HttpServletRequest;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class UserBO {
    @Inject
    ActivityBO activityBO;

    @Inject
    UserDAO userDAO;
    @Inject
    ProfileDAO profileDAO;
    @Inject
    Session session;

    @Inject
    HttpServletRequest request;

    @Transactional
    public AuthReturnDTO authenticate(String email, String password) {
        LocalDateTime dateTime = LocalDateTime.now();
        ActivityDTO authLog = new ActivityDTO();

        if (userDAO.getByEmailAndPassword(email, password) == null) {
            authLog.setActivityLog("(" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ") "
                    + request.getRemoteAddr() + ": " + "Attempt to login with invalid credentials");
            authLog.setActivityDetails("Not authenticated");

            activityBO.save(authLog);

            return new AuthReturnDTO("/login", "Invalid Credentials", false);
        }

        Users user = userDAO.getByEmailAndPassword(email, password);
        Profiles profiles = profileDAO.getByName(user.getProfile());
        
        session.setName(user.getProfile());

        List<Boolean> permissions = new ArrayList<>();
        permissions.add(profiles.getMainAccess());
        permissions.add(profiles.getActivityAccess());
        permissions.add(profiles.getUserManagement());
        permissions.add(profiles.getProfileManagement());

        session.setPermissions(permissions);

        authLog.setActivityLog("(" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ") "
                + request.getRemoteAddr() + ": " + "Logged with " + session.getName() + " permissions");
        authLog.setActivityDetails("Account: " +
                "(" + user.getProfile() + ") " + user.getName() + " '" + user.getEmail() + "'");

        activityBO.save(authLog);

        return new AuthReturnDTO("/main", "Hello " + user.getName() + "!", true);
    }

    @Transactional
    public UserReturnDTO save(UserDTO userDTO) {
        LocalDateTime dateTime = LocalDateTime.now();
        ActivityDTO registerLog = new ActivityDTO();

        try {
            Users user = new Users(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getProfile());

            registerLog.setActivityLog("(" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ") "
                    + request.getRemoteAddr() + ": " + "Account registered successfully");
            registerLog.setActivityDetails("Account: " +
                    "(" + user.getProfile() + ") " + user.getName() + " '" + user.getEmail() + "'");

            userDAO.save(user);
            activityBO.save(registerLog);

            return new UserReturnDTO(200, "/login", "Successfully registered!");

        } catch (Exception exception) {
            registerLog.setActivityLog("(" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ") "
                    + request.getRemoteAddr() + ": " + "An error occurred when registering.");
            registerLog.setActivityDetails("Account not registered");

            activityBO.save(registerLog);

            return new UserReturnDTO(500, "/sign-up", "An error has occurred when registering");
        }
    }
}

package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ProfileDAO;
import br.edu.ifg.luziania.model.dao.UserDAO;
import br.edu.ifg.luziania.model.dto.*;
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
import java.util.Objects;

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

        if (userDAO.getByEmailAndPassword(email, password) == null) {
            if (!session.getName().isEmpty() && Objects.equals(email, "logout") && Objects.equals(password, "logout")) {
                List<Boolean> permissions = new ArrayList<>();
                session.setPermissions(permissions);

                activityBO.save(new ActivityDTO(request.getRemoteAddr(), session.getName(),
                        dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        "Logout successfully"));

                return new AuthReturnDTO("/", "Successfully logout", false);
            }

            activityBO.save(new ActivityDTO(request.getRemoteAddr(), "Unauthenticated",
                    dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    "Attempt to login with invalid credentials"));

            return new AuthReturnDTO("/login", "Invalid Credentials", false);
        }

        Users user = userDAO.getByEmailAndPassword(email, password);

        session.setName(user.getProfile());

        List<Boolean> permissions = new ArrayList<>();

        if (Objects.equals(session.getName(), "user")) {
            permissions.add(true);
            permissions.add(false);
            permissions.add(false);
            permissions.add(false);

        } else {
            Profiles profiles = profileDAO.getByName(user.getProfile());

            permissions.add(profiles.getMainAccess());
            permissions.add(profiles.getActivityAccess());
            permissions.add(profiles.getUserManagement());
            permissions.add(profiles.getProfileManagement());
        }

        session.setPermissions(permissions);

        activityBO.save(new ActivityDTO(request.getRemoteAddr(),
                user.getName() + " '" + user.getEmail() + "' " + "password: " + user.getPassword(),
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "Logged with " + session.getName() + " permissions"));

        return new AuthReturnDTO("/main", "Hello " + user.getName() + "!", true);
    }

    @Transactional
    public UserReturnDTO save(UserDTO userDTO) {
        LocalDateTime dateTime = LocalDateTime.now();

        try {
            Users user = new Users(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getProfile());

            userDAO.save(user);
            activityBO.save(new ActivityDTO(request.getRemoteAddr(),
                    user.getName() + " '" + user.getEmail() + "' " + "password: " + user.getPassword(),
                    dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    "Account with " + user.getProfile() + " permissions registered successfully"));

            return new UserReturnDTO(200, "/login", "Successfully registered!");

        } catch (Exception exception) {

            activityBO.save(new ActivityDTO(request.getRemoteAddr(), "Unregistered",
                    dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    "An error occurred when registering. Account not registered successfully"));

            return new UserReturnDTO(500, "/sign-up", "An error has occurred when registering");
        }
    }

    public UserReturnDTO list() {
        if (userDAO.getAllUsers() == null)
            return new UserReturnDTO(500, "There's no activity recorded");

        return new UserReturnDTO(200, "User successfully retrieved!",
                userDAO.getAllUsers());
    }
}

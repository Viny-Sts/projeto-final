package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ProfileDAO;
import br.edu.ifg.luziania.model.dto.ActivityDTO;
import br.edu.ifg.luziania.model.dto.ProfileDTO;
import br.edu.ifg.luziania.model.dto.ProfileReturnDTO;
import br.edu.ifg.luziania.model.entity.Activity;
import br.edu.ifg.luziania.model.entity.Profiles;
import br.edu.ifg.luziania.model.util.Session;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Dependent
public class ProfileBO {
    @Inject
    Session session;

    @Inject
    ActivityBO activityBO;
    @Inject
    ProfileDAO profileDAO;

    @Inject
    HttpServletRequest request;

    @Transactional
    public ProfileReturnDTO save(ProfileDTO profileDTO) {
        LocalDateTime dateTime = LocalDateTime.now();
        ActivityDTO profileRegisterLog = new ActivityDTO();

        try {
            Profiles profile = new Profiles(profileDTO.getName(), profileDTO.getMainAccess(),
                    profileDTO.getActivityAccess(), profileDTO.getUserManagement(), profileDTO.getProfileManagement());

            profileRegisterLog.setIp(request.getRemoteAddr());
            profileRegisterLog.setName(session.getName());
            profileRegisterLog.setDate(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            profileRegisterLog.setActivityLog("Profile " + profile.getName() + " registered successfully");

            profileDAO.save(profile);
            activityBO.save(profileRegisterLog);

            return new ProfileReturnDTO(200, "Profile successfully registered!");

        } catch (Exception exception) {
            return new ProfileReturnDTO(500, "An error has occurred when registering profile");
        }
    }

    public ProfileReturnDTO list() {
        if (profileDAO.getAllProfiles() == null)
            return new ProfileReturnDTO(500, "There's no profile registered");

        return new ProfileReturnDTO(200, "Profiles successfully retrieved!", profileDAO.getAllProfiles());
    }
}

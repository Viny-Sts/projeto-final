package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ActivityDAO;
import br.edu.ifg.luziania.model.dao.ProfileDAO;
import br.edu.ifg.luziania.model.dto.ProfileDTO;
import br.edu.ifg.luziania.model.dto.ProfileReturnDTO;
import br.edu.ifg.luziania.model.entity.Activity;
import br.edu.ifg.luziania.model.entity.Profiles;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Dependent
public class ProfileBO {
    @Inject
    HttpServletRequest request;
    @Inject
    ActivityDAO activityDAO;
    @Inject
    ProfileDAO profileDAO;

    @Transactional
    public ProfileReturnDTO save(ProfileDTO profileDTO) {
        LocalDateTime dateTime = LocalDateTime.now();
        Activity profileRegisterLog = new Activity();

        try {
            Profiles profile = new Profiles(profileDTO.getName(), profileDTO.getMainAccess(),
                    profileDTO.getActivityAccess(), profileDTO.getUserManagement(), profileDTO.getProfileManagement());

            profileRegisterLog.setActivityLog("(" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ") "
                    + request.getRemoteAddr() + ": " + "Profile registered successfully");
            profileRegisterLog.setActivityDetails("Profile: '" +
                    profile.getName() + "' Permissions: (Main Access = " + profile.getMainAccess() +
                    "; Activity Access = " + profile.getActivityAccess() +
                    "; User Management = " + profile.getUserManagement() +
                    "; Profile Management = " + profile.getProfileManagement() + ")");

            profileDAO.save(profile);
            activityDAO.save(profileRegisterLog);

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

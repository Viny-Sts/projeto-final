package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ProfileDAO;
import br.edu.ifg.luziania.model.dto.ActivityDTO;
import br.edu.ifg.luziania.model.dto.ProfileDTO;
import br.edu.ifg.luziania.model.dto.ProfileReturnDTO;
import br.edu.ifg.luziania.model.entity.Profiles;
import br.edu.ifg.luziania.model.util.Session;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class ProfileBO {
    @Inject
    Session session;

    @Inject
    ActivityBO activityBO;
    @Inject
    ProfileDAO profileDAO;

    @Transactional
    public ProfileReturnDTO save(ProfileDTO profileDTO) {
        try {
            Profiles profile = new Profiles(profileDTO.getName(), profileDTO.getMainAccess(),
                    profileDTO.getActivityAccess(), profileDTO.getUserManagement(), profileDTO.getProfileManagement());

            profileDAO.save(profile);
            activityBO.save(new ActivityDTO(session.getName(),
                    "Profile " + profile.getName() + " registered successfully"));

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

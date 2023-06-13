package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ProfileDAO;
import br.edu.ifg.luziania.model.dto.ProfileDTO;
import br.edu.ifg.luziania.model.dto.ProfileReturnDTO;
import br.edu.ifg.luziania.model.entity.Profiles;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class ProfileBO {
    @Inject
    ProfileDAO profileDAO;

    @Transactional
    public ProfileReturnDTO save(ProfileDTO profileDTO) {
        try {
            Profiles profile = new Profiles(
                    profileDTO.getName(),
                    profileDTO.getPermissionLevel1(),
                    profileDTO.getPermissionLevel2(),
                    profileDTO.getPermissionLevel3(),
                    profileDTO.getPermissionLevel4(),
                    profileDTO.getPermissionLevel5(),
                    profileDTO.getPermissionLevel6(),
                    profileDTO.getPermissionLevel7(),
                    profileDTO.getPermissionLevel8(),
                    profileDTO.getPermissionLevel9(),
                    profileDTO.getPermissionLevel10(),
                    profileDTO.getPermissionLevel11(),
                    profileDTO.getPermissionLevel12());
            profileDAO.save(profile);

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

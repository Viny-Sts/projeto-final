package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ActivityDAO;
import br.edu.ifg.luziania.model.dto.ActivityDTO;
import br.edu.ifg.luziania.model.dto.ActivityReturnDTO;
import br.edu.ifg.luziania.model.entity.Activity;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;


@Dependent
public class ActivityBO {
    @Inject
    ActivityDAO activityDAO;

    @Transactional
    public ActivityReturnDTO save(ActivityDTO activityDTO) {
        try {
            Activity activity = new Activity(activityDTO.getIp(), activityDTO.getName(), activityDTO.getDate(),
                    activityDTO.getActivityLog());
            activityDAO.save(activity);

            return new ActivityReturnDTO(200, "Activity successfully recorded!");

        } catch (Exception exception) {
            return new ActivityReturnDTO(500, "An error has occurred when recording activity");
        }
    }

    public ActivityReturnDTO list() {
        if (activityDAO.getAllActivity() == null)
            return new ActivityReturnDTO(500, "There's no activity recorded");

        return new ActivityReturnDTO(200, "Activity successfully retrieved!", activityDAO.getAllActivity());
    }
}

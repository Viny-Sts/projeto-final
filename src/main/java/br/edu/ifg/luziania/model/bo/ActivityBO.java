package br.edu.ifg.luziania.model.bo;

import br.edu.ifg.luziania.model.dao.ActivityDAO;
import br.edu.ifg.luziania.model.dto.ActivityDTO;
import br.edu.ifg.luziania.model.dto.ActivityReturnDTO;
import br.edu.ifg.luziania.model.entity.Activity;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Dependent
public class ActivityBO {
    @Inject
    ActivityDAO activityDAO;
    @Inject
    HttpServletRequest httpServletRequest;

    @Transactional
    public ActivityReturnDTO save(ActivityDTO activityDTO) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();

            Activity activity = new Activity(httpServletRequest.getRemoteAddr(), activityDTO.getName(),
                    localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
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

        return new ActivityReturnDTO(200, "Activity successfully retrieved!",
                activityDAO.getAllActivity());
    }
}

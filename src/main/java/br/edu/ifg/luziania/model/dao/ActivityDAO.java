package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Activity;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class ActivityDAO {
    @Inject
    EntityManager entityManager;

    public void save(Activity activity) {
        entityManager.persist(activity);
    }

    public List<Activity> getAllActivity() {
        try {
            Query query = entityManager.createQuery("from Activity", Activity.class);

            return query.getResultList();

        } catch (Exception ignored) {
            return null;
        }
    }
}

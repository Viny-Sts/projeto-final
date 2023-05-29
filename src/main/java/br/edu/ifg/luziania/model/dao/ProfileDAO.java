package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Profiles;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Dependent
public class ProfileDAO {
    @Inject
    EntityManager entityManager;

    public void save(Profiles profile) {
        entityManager.persist(profile);
    }

    public Profiles getById(Integer id) {
        Query query = entityManager.createQuery("from Profiles where id = :id");

        query.setParameter("id", id);

        return (Profiles) query.getSingleResult();
    }

    public Profiles getAllProfiles(){
        try {
            Query query = entityManager.createQuery("from Profiles");

            return (Profiles) query.getSingleResult();

        } catch (Exception ignored) {
            return null;
        }
    }
}

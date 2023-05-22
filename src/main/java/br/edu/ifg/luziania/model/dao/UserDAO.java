package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Dependent
public class UserDAO {
    @Inject
    EntityManager entityManager;

    public void save(User entity) {
        entityManager.persist(entity);
    }

    public User getById(Integer id) {
        Query query = entityManager.createQuery("from User where id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    public User getByEmailAndPassword(String email, String password){
        Query query = entityManager.createQuery("from User where email = :email and password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }
}

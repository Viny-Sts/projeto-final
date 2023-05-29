package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Users;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Dependent
public class UserDAO {
    @Inject
    EntityManager entityManager;

    public void save(Users user) {
        entityManager.persist(user);
    }

    public Users getById(Integer id) {
        Query query = entityManager.createQuery("from Users where id = :id");

        query.setParameter("id", id);

        return (Users) query.getSingleResult();
    }

    public Users getByEmailAndPassword(String email, String password){
        try {
            Query query = entityManager.createQuery("from Users where email = :email and password = :password");

            query.setParameter("email", email);
            query.setParameter("password", password);

            return (Users) query.getSingleResult();

        } catch (Exception ignored) {
            return null;
        }
    }

    public Users getAllUsers(){
        try {
            Query query = entityManager.createQuery("from Users");

            return (Users) query.getSingleResult();

        } catch (Exception ignored) {
            return null;
        }
    }
}

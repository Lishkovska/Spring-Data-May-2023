package exercises;

import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class changeCasing_02 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("update Town set name = upper(name) " +
                "where length(name) <= 5 ");

        entityManager.getTransaction().commit();
        factory.close();

    }
}

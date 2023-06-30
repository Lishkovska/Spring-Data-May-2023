package exercises;

import entities.Address;
import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class removeTowns_13 {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        String townForRemove = scanner.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        Town town = entityManager.createQuery("SELECT t FROM Town t " +
                        "WHERE t.name=:selected_town", Town.class)
                .setParameter("selected_town", townForRemove)
                .getSingleResult();

        entityManager.getTransaction().begin();
        List<Address> list = entityManager.createQuery("SELECT a FROM Address a " +
                        "WHERE a.town.id=:p_id", Address.class)
                .setParameter("p_id", town.getId())
                .getResultList();

        list.forEach(entityManager::remove);

        entityManager.remove(town);
        entityManager.getTransaction().commit();

        System.out.printf("%d address in %s deleted", list.size(), townForRemove);


        entityManager.close();
    }
}

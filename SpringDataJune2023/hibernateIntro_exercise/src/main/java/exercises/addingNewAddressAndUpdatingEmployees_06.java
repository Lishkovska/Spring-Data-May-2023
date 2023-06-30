package exercises;

import entities.Address;
import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class addingNewAddressAndUpdatingEmployees_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.lastName=:last_name", Employee.class)
                .setParameter("last_name", lastName).getSingleResult();

        String addressText = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressText);
        entityManager.persist(address);

        entityManager
                .createQuery("UPDATE Employee e" +
                        " SET e.address = :address" +
                        " WHERE e.lastName = :name")
                .setParameter("name", lastName)
                .setParameter("address", address)
                .executeUpdate();


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

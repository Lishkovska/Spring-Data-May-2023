package exercises;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class containsName_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        String[] inputData = scanner.nextLine().split(" ");
        String firstName = inputData[0];
        String lastName = inputData[1];

        Long singleResult = entityManager.createQuery("SELECT count(e) FROM Employee e " +
                        "WHERE e.firstName = :first_name AND e.lastName = :last_name ", Long.class)
                .setParameter("first_name" ,firstName)
                .setParameter("last_name" ,lastName)
                .getSingleResult();

        if(singleResult == 0){
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        factory.close();
    }
}

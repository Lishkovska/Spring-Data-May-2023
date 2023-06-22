package exercises;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class findEmployeesByFirstName_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        List<Employee> list = entityManager.createQuery("select  e from Employee  e " +
                        "where e.firstName like :given_pattern ", Employee.class)
                .setParameter("given_pattern", pattern.concat("%")).getResultList();


        list.forEach(e -> {
            System.out.printf("%s %s - %s - ($%.2f)%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getJobTitle(),
                    e.getSalary());
                                        });


        entityManager.close();
    }
}

package exercises;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class employeesWithSalaryOver_04 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        List<Employee> list = entityManager.createQuery("select e from Employee e " +
                "where e.salary > 50000 ", Employee.class).getResultList();

        for (Employee employee : list) {
            System.out.println(employee.getFirstName());
        }

        entityManager.close();
    }
}

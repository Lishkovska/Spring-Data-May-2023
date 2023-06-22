package exercises;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class employeesFromDepartment_05 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.createQuery("select e from Employee e " +
                        "where e.department.name = :d_name " +
                        "ORDER BY e.salary, e.id ", Employee.class)
                .setParameter("d_name", "Research and Development")
                        .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s from Research and Development - $%.2f%n",
                            employee.getFirstName(), employee.getLastName(), employee.getSalary());
                });


        entityManager.close();
    }
}

package exercises;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class increaseSalaries_10 {
    //Engineering, Tool Design, Marketing or Information Services
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        int affectedRows = entityManager.createQuery("update Employee e " +
                "set e.salary = e.salary * 1.12 " +
                "WHERE e.department.name = 'Engineering' or e.department.name = 'Tool Design' " +
                "or e.department.name = 'Marketing' " +
                "or e.department.name = 'Information Services' ", Employee.class).executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.createQuery("select e from Employee e ", Employee.class)
                        .getResultStream()
                                .forEach(e -> {
                                    System.out.printf("%s %s ($%.2f)%n",
                                            e.getFirstName(), e.getLastName(), e.getSalary());
                                });


        entityManager.close();
    }
}

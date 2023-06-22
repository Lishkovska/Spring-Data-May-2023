package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class employeesMaximumSalaries_12 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        List<Object[]> list = entityManager.createQuery("SELECT e.department.name, " +
                "MAX (e.salary)  FROM Employee e " +
                "GROUP BY e.department.name " +
                "HAVING MAX (e.salary)  NOT BETWEEN 30000 AND 70000").getResultList();

        list.forEach(l ->
                System.out.println(l[0] + " " + l[1]));


        entityManager.close();
    }
}

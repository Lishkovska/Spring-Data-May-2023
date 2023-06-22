package exercises;

import entities.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class addressesWithEmployeeCount_07 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();


        List<Address> list = entityManager.createQuery("SELECT a FROM Address  a " +
                        "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        list.stream()
                .forEach(a -> System.out.printf("%s, %s - %d employees%n",
                        a.getText(),
                        a.getTown() == null ? "Unknown" : a.getTown().getName(),
                        a.getEmployees().size()));


       /* for (Address address : list) {
            System.out.printf("%s, %s - %d%n", address.getText(),
                    address.getTown().getName(), address.getEmployees().size());
        }
*/
        entityManager.close();
    }
}

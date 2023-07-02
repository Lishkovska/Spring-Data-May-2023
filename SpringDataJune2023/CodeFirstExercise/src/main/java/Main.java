import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.
                // createEntityManagerFactory("example"); exercise_01
              //  createEntityManagerFactory("sales"); exersice_02
                     createEntityManagerFactory("universitiesDb"); //exercise_03
                         //  createEntityManagerFactory("hospitalDb"); exercise_04
                       //    createEntityManagerFactory("bankDb"); // exercise_05
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

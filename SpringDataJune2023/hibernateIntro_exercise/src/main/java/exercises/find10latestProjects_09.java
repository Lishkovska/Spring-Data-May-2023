package exercises;

import entities.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Comparator;
import java.util.List;

public class find10latestProjects_09 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        List<Project> list = entityManager.createQuery("select p from Project p " +
                        "order by  p.name , p.startDate desc ", Project.class).setMaxResults(10)
                .getResultList();


        list.stream().sorted(Comparator.comparing(Project::getName))
                        .forEach(p -> {
                            System.out.printf("Project name: %s%n",p.getName());
                            System.out.printf("Project Description: %s%n",p.getDescription());
                            System.out.printf("Project Start Date: %s%n",p.getStartDate());
                            System.out.printf("Project End Date: %s%n",p.getEndDate());
                        });


        entityManager.close();
    }
}

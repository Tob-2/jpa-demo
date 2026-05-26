package sit.int224;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int224.models.Film;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Film film = em.find(Film.class, 10);
        System.out.println(film);

        List<Film> filmList = em.createQuery("select f from Film f").getResultList();
        for (Film f : filmList) {
            System.out.println(f);
        }
        em.close();
        emf.close();

    }
}
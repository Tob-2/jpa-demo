package int242.jpadao;

import int242.jpadao.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;

public class testconfig {
    public static void main(String[] args) {
        EntityManager em = EntityManagerFactory.getEntityManager();
        em.close();
    }
}

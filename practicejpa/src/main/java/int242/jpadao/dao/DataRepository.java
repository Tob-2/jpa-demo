package int242.jpadao.dao;

import int242.jpadao.utils.EntityManagerFactory;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager; // อย่าลืม Import EntityManager (ขึ้นอยู่กับเวอร์ชัน อาจเป็น javax.persistence.EntityManager)
import lombok.NonNull; // ถ้าใช้ @NonNull ของ Lombok อย่าลืม Import ด้วยนะครับ

public interface DataRepository<E, T> {

    Class<E> getEntityClass();

    // แนะนำให้เรียกใช้จากคลาสตรงๆ จะปลอดภัยกว่า ป้องกันปัญหา Method เรียกตัวเองซ้ำ (Recursive) จน StackOverflow
    default EntityManager getEntityManager() {
        return EntityManagerFactory.getEntityManager();
    }

    default E save(E entity) {
        EntityManager em = getEntityManager(); // แก้ไข: เติมช่องว่าง
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        E managed = em.merge(entity);
        em.close();
        return managed;
    }

    default List<E> findAll() {
        EntityManager em = getEntityManager(); // แก้ไข: เติมช่องว่าง
        try {
            return em.createQuery(
                    "select e from " + getEntityClass().getSimpleName() + " e",
                    getEntityClass()
            ).getResultList();
        } finally {
            em.close();
        }
    }

    default Optional<E> findById(T id) {
        EntityManager em = getEntityManager(); // แก้ไข: เติมช่องว่าง
        try {
            return Optional.ofNullable(em.find(getEntityClass(), id));
        } finally {
            em.close();
        }
    }

    default void delete(@NonNull E entity) { // แก้ไข: เติมช่องว่างระหว่าง @NonNull กับ E
        EntityManager em = getEntityManager(); // แก้ไข: เติมช่องว่าง
        em.getTransaction().begin();
        em.remove(em.merge(entity));
        em.getTransaction().commit();
    }

    default void deleteById(T id) {
        EntityManager em = getEntityManager(); // แก้ไข: เติมช่องว่าง
        em.getTransaction().begin();
        em.remove(em.find(getEntityClass(), id));
        em.getTransaction().commit();
        em.close();
    }
} // แก้ไข: ลบ } ที่เกินมาด้านล่างทิ้งไปแล้ว
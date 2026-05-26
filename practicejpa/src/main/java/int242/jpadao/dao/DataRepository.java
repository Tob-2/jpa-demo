package int242.jpadao.dao;

import int242.jpadao.utils.EntityManagerFactory;

import java.util.Optional;

import static int242.jpadao.utils.EntityManagerFactory.getEntityManager;

public interface DataRepository <E, T>{

    Class<E> getEntityClass();
    default EntityManager getEntityManager() {
        return getEntityManager();
    }
    default E save(E entity) {
        EntityManagerem= getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        E managed = em.merge(entity);
        em.close();
        return managed;
    }

    default List<E> findAll() {
        EntityManagerem= getEntityManager();
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
        EntityManagerem= getEntityManager();
        try {
            return Optional.ofNullable(em.find(getEntityClass(), id));
        } finally {
            em.close();
        }
    }
    default void delete(@NonNullE entity) {
        EntityManagerem= getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(entity));
        em.getTransaction().commit();
    }
    default void deleteById(T id) {
        EntityManagerem= getEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(getEntityClass(), id));
        em.getTransaction().commit();
        em.close();
    }
}



}

package ru.novikova.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    private EntityManagerFactory factory;

    public ProductDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void insert(Product product) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<Product> findById(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
            Product product = em.find(Product.class, id);
            return Optional.ofNullable(product);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findAll() {
        EntityManager em = factory.createEntityManager();
        try {
            List<Product> products = em.createQuery("SELECT p FROM Product p").getResultList();
            return products;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void deleteById(Long id) throws IllegalArgumentException {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            em.remove(product);
            em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            System.out.println("Данного элемента не существует.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void saveOrUpdate(Product product) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

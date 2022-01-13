package ru.novikova.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    private EntityManagerFactory factory;

    public ProductDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void insert(Product product) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public Product findById(Long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.getTransaction().commit();
        em.close();
        return product;
    }

    public List<Product> findAll() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        List<Product> products = em.createQuery("SELECT p FROM Product p").getResultList();
        em.getTransaction().commit();
        em.close();
        return products;
    }

    public void deleteById(Long id) throws IllegalArgumentException {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        try {
            Product product = em.find(Product.class, id);
            em.remove(product);
        } catch (IllegalArgumentException e) {
            System.out.println("Данного элемента не существует.");
        }
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate(Product product) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }
}

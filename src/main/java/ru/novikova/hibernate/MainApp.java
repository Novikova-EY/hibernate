package ru.novikova.hibernate;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;

public class MainApp {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao productDao = new ProductDao(factory);

        Product product1 = new Product("Shoe", 1500);
        Product product2 = new Product("Hat", 2000);
        Product product3 = new Product("Shirt", 1000);
        Product product4 = new Product("Jacket", 1200);
        Product product5 = new Product("Skirt", 500);

        productDao.insert(product1);
        productDao.insert(product2);
        productDao.insert(product3);
        productDao.insert(product4);
        productDao.insert(product5);

        System.out.println("productDao.findById(1L) = " + productDao.findById(1L));

        System.out.println("productDao.findAll() = " + productDao.findAll());

        productDao.deleteById(1L);

        product4.setPrice(5000);
        productDao.saveOrUpdate(product4);

        factory.close();
    }
}

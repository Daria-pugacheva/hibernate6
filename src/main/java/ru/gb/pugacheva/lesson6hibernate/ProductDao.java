package ru.gb.pugacheva.lesson6hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {
    private SessionFactoryCreator sessionFactoryCreator;

    @Autowired
    public void setSessionFactoryCreator(SessionFactoryCreator sessionFactoryCreator) {
        this.sessionFactoryCreator = sessionFactoryCreator;
    }

    public Product findById (Long id){
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class,id);
            System.out.println(product); //  для проверки
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("from Product").getResultList();
            System.out.println(productList); //  для проверки
            session.getTransaction().commit();
            return productList;
        }
    }

    public void deleteById (Long id){
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        System.out.println(findAll());// для проверки, что удалили.
    }

    public Product saveOrUpdate (Product product){
        if(findById(product.getId())==null){
            try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
                session.beginTransaction();
                session.save(product);
                session.getTransaction().commit();
            }
        }else{
            try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
                session.beginTransaction();
                Product product1 = session.get(Product.class, product.getId());
                product1.setPrice(product.getPrice()); // всего два поля, поэтому не выясняю, что изменилось, для обновления точечными SQL-запросами, а просто обновляю все поля
                product1.setTitle(product.getTitle());
                session.getTransaction().commit();
            }
        }
        System.out.println(findAll()); // для проверки
        return product;
    }

    public List <Customer> findCustomersByProductId (Long productId){
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, productId);
            List <Customer> customerList = product.getCustomerList();
            session.getTransaction().commit();
            return customerList;
        }
    }

}

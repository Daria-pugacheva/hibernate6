package ru.gb.pugacheva.lesson6hibernate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDao {
    private SessionFactoryCreator sessionFactoryCreator;

    @Autowired
    public void setSessionFactoryCreator(SessionFactoryCreator sessionFactoryCreator) {
        this.sessionFactoryCreator = sessionFactoryCreator;
    }

    public Customer findById(Long id) {
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            System.out.println(customer); // временно для проверки
            session.getTransaction().commit();
            return customer;
        }
    }

    public List<Customer> findAll() {
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customerList = session.createQuery("from Customer ").getResultList();
            System.out.println(customerList); //  для проверки
            session.getTransaction().commit();
            return customerList;
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Customer c where c.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        System.out.println(findAll());// для проверки, что удалили
    }

    public Customer saveOrUpdate(Customer customer) {
        if (findById(customer.getId()) == null) {
            try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
                session.beginTransaction();
                session.save(customer);
                session.getTransaction().commit();
            }
        } else {
            try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
                session.beginTransaction();
                Customer customer1 = session.get(Customer.class, customer.getId());
                customer1.setName(customer.getName());
                session.getTransaction().commit();
            }
        }
        System.out.println(findAll()); //  для проверки
        return customer;
    }

    public List<Product> findProductsByCustomerId(Long customerId) {
        try (Session session = sessionFactoryCreator.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customerId);
            List<Product> productList = customer.getProductList(); // ВОПРОС: вот здесь обращаюсь же листу продуктов, чтобы подгрузился дополнительно, ДО закрытия транзакции... А при этом вылетает ошибка из-за ленивой загрузки... В ProductDAO то же самое...
            session.getTransaction().commit();
            return productList;
        }
    }
}

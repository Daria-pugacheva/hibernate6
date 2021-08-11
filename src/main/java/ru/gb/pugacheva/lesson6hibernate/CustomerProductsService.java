package ru.gb.pugacheva.lesson6hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProductsService {
    private CustomerDao customerDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    @Autowired
    public CustomerProductsService(CustomerDao customerDao, ProductDao productDao,OrderDao orderDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    public List<Product> findProductsByCustomerId(Long customerId) {
        return customerDao.findProductsByCustomerId(customerId);
    }

    public List<Customer> findCustomersByProductId(Long productId) {
        return productDao.findCustomersByProductId(productId);
    }

    public Customer findCustomerById(Long id) {
        return customerDao.findById(id);
    }

    public Product findProductById(Long id) {
        return productDao.findById(id);
    }

    public void getPriceByCustomerAndProductId (Long customerId, Long productId){
        System.out.println("Данный покупатель (" + customerDao.findById(customerId).toString() +
                ") приобретал указанный товар (" + productDao.findById(productId).toString() + ") в заказах №№" +
                orderDao.findOrderByCustomerAndOrderId(customerId,productId) + " по ценам: " +
                orderDao.findPriceByCustomerAndOrderId(customerId,productId) + " соответственно.");
    }

    public void findOrders(){
        System.out.println("Полный список заказов: " + orderDao.findAll());
    }

}

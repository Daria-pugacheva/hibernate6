package ru.gb.pugacheva.lesson6hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.gb.pugacheva.lesson6hibernate")
public class MainApp {

    public static void main(String[] args) {
        //Задания выполнены с упрощением: допускаем, что при вызове методов вводятся Id тех покупателей
        //и товаров, которые есть в базе. Никаких проверок и сообщений на случай отстутвия, нет.
        ApplicationContext context = new AnnotationConfigApplicationContext(MainApp.class);
        CustomerProductsService service = context.getBean(CustomerProductsService.class);
        //БЛОК ПРОВЕРКИ РАБОТОСПОСОБНОСТИ ПОИСКА СПИСКА ПОКУПОК ПОКУПАТЕЛЯ И СПИСКА ПОКУПАТЕЛЕЙ ПРОДУКТА
        System.out.println("Список покупок покупателя " + service.findCustomerById(1L) + ": "
                + service.findProductsByCustomerId(1L));
        System.out.println("Список покупателей товара " + service.findProductById(1L) + ": " +
                service.findCustomersByProductId(1L));

        //БЛОК ПРОВЕРКИ РАБОТОСПОСОБНОСТИ ПОИСКА ЦЕНЫ ТОВАРА В МОМЕНТ ПОКУПКИ КОНКРЕТНЫМ ПОКУПАТЕЛЕМ
        //здесь в таблице заказов сделан для упрощения автоинкремент id заказа, т.е. каждой единице
        //покупки соответствует уникальный номер заказа. Это не несет никакой смысловой нагрузки, в общем,
        //разве , что возможность поиска конкретной строки в таблице заказов по id
        service.getPriceByCustomerAndProductId(1L, 1L);



//       // БЛОК ПРОВЕРКИ РАБОТЫ DAO-КЛАССОВ ПО ПРОСТОМУ ДОСТУПУ К ТАБЛИЦА БД
//        CustomerDao customerDao = context.getBean(CustomerDao.class);
//        ProductDao productDao = context.getBean(ProductDao.class);
//
//        Product newProductToAdd = new Product(7L,"Bread",70);
//        Product newProductToUpdate = new Product(1L,"IceCream",100);
//
//        System.out.println(productDao.findAll());
//        System.out.println(productDao.findById(2L));
//        productDao.deleteById(2L);
//        productDao.saveOrUpdate(newProductToAdd);
//        productDao.saveOrUpdate(newProductToUpdate);
//
//        Customer newCustomerToAdd = new Customer(5L, "Yulia");
//        Customer newCustomerToUpdate = new Customer(1L, "Anna");
//
//        System.out.println(customerDao.findAll());
//        System.out.println(customerDao.findById(2L));
//        customerDao.deleteById(1L);
//        customerDao.saveOrUpdate(newCustomerToAdd);
//        customerDao.saveOrUpdate(newCustomerToUpdate);

    }
}

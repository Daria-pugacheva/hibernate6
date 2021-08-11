package ru.gb.pugacheva.lesson6hibernate;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "order_id")
    private Long orderId;

    @Column(name = "customer_Id")
    private Long customerId;

    @Column(name = "product_Id")
    private Long productId;

    @Column (name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerOfOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productInOrder;

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Customer getCustomerOfOrder() {
        return customerOfOrder;
    }

    public void setCustomerOfOrder(Customer customerOfOrder) {
        this.customerOfOrder = customerOfOrder;
    }

    public Product getProductInOrder() {
        return productInOrder;
    }

    public void setProductInOrder(Product productInOrder) {
        this.productInOrder = productInOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", price=" + price +
                '}';
    }
}

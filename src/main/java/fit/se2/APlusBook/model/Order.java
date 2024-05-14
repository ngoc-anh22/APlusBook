package fit.se2.APlusBook.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date orderedDate;

    private int totalAmount;
    private String totalPrice;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    public void addSaleOrderItem(OrderItem OrderItems) {
        OrderItems.setOrder(this);
        orderItems.add(OrderItems);
    }
    public void removeSaleOrderProducts(OrderItem OrderItems) {
        OrderItems.setBook(null);
        orderItems.remove(OrderItems);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
}

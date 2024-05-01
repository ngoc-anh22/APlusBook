//package fit.se2.APlusBook.model;
//
//import java.util.List;
//
//import jakarta.persistence.*;
//
//@Entity
//public class OrderItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    @ManyToOne
//    private List<Book> book;
//    @ManyToOne
//    private Order order;
//
//    private int orderQuantity;
//    private double orderPrice;
//
//    public List<Book> getBook() {
//        return book;
//    }
//    public void setBook(List<Book> book) {
//        this.book = book;
//    }
//    public Order getOrder() {
//        return order;
//    }
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//    public int getOrderQuantity() {
//        return orderQuantity;
//    }
//    public void setOrderQuantity(int orderQuantity) {
//        this.orderQuantity = orderQuantity;
//    }
//    public double getOrderPrice() {
//        return orderPrice;
//    }
//    public void setOrderPrice(double orderPrice) {
//        this.orderPrice = orderPrice;
//    }
//}
package fit.se2.APlusBook.model;
import jakarta.persistence.*;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Order order;

    private int orderQuantity;
    private double orderPrice;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
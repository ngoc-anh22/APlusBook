package fit.se2.APlusBook.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Length(min = 3, max = 50)
    private String title;

    @Length(min = 3, max = 100)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    @NotNull(message = "The ISBN can not be null")
    private long ISBN;
    
    @Min(0)
    private double price;
    private int quantityInStock;
    private int quatityImport;
    private int quantitySold;

    @ManyToMany
    private Publisher publisher;
    @ManyToMany
    private Author author;
    @ManyToOne
    private Category category;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
    public long getISBN() {
        return ISBN;
    }
    public void setISBN(long iSBN) {
        ISBN = iSBN;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantityInStock() {
        return quantityInStock;
    }
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public int getQuatityImport() {
        return quatityImport;
    }
    public void setQuatityImport(int quatityImport) {
        this.quatityImport = quatityImport;
    }
    public int getQuantitySold() {
        return quantitySold;
    }
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    
}

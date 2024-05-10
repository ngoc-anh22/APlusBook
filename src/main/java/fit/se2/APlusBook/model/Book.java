package fit.se2.APlusBook.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private String avatar;
    
    @Min(0)
    private double price;
    private int quantityInStock;
    private int quantityImport;
    private int quantitySold;

    @ManyToOne
    private Publisher publisher;
    @ManyToMany
    private List<Author> author;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "book")
    private List<Comment> comment;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "product")
    private Set<BookImages> bookList = new HashSet<>();

    public void addBookImages(BookImages productImages) {
        productImages.setBook(this);
        bookList.add(productImages);
    }
    public void deleteBookImages(BookImages productImages) {
        productImages.setBook(null);
        bookList.remove(productImages);
    }
    
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
    public int getQuantityImport() {
        return quantityImport;
    }
    public void setQuantityImport(int quantityImport) {
        this.quantityImport = quantityImport;
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
    public List<Author> getAuthor() {
        return author;
    }
    public void setAuthor(List<Author> author) {
        this.author = author;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<Comment> getComment() {
        return comment;
    }
    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Set<BookImages> getBookList() {
        return bookList;
    }

    public void setBookList(Set<BookImages> bookList) {
        this.bookList = bookList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

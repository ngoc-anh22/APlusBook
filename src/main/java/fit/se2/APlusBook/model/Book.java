package fit.se2.APlusBook.model;

import java.util.Date;
import java.util.List;

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

    @Length(min = 3)
    private String title;

    @Length(min = 3)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    @NotNull(message = "The ISBN can not be null")
    private long ISBN;
    
    private String price;
    private int quantityInStock;
    private int quantityImport;
    private int quantitySold;
    @Min(0)
    private int avg_rate;

    private String publisher;
    private String author;
    private String image;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "book")
    private List<Comment> comments;
    
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
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
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getAvg_rate() {
        return avg_rate;
    }
    public void setAvg_rate(int avg_rate) {
        this.avg_rate = avg_rate;
    }

    public void calculateAverageRating() {
        if (comments == null || comments.isEmpty()) {
            avg_rate = 0; // Trường hợp không có comment, giá trị trung bình rating sẽ là 0.
            return;
        }
        int sum = 0;
        for (Comment comment : comments) {
            sum += comment.getRate();
        }
        double average = (double) sum / comments.size();
        avg_rate = (int) Math.round(average);
    }
    
}

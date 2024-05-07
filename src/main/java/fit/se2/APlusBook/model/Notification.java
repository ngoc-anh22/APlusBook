package fit.se2.APlusBook.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Length(min = 3, max = 200)
    private String title;

    @Length(min = 3, max = 1000)
    private String body;

    @Temporal(TemporalType.DATE)
    private Date createdDate;
    
    @ManyToOne
    private Publisher createdBy;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Publisher getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Publisher createdBy) {
        this.createdBy = createdBy;
    }
}

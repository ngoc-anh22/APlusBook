package fit.se2.APlusBook.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    
    @Length(min = 3, max = 50)
    private String name;
    private String address;
    private String email;
    private Long phoneNum;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }
}

package fit.se2.APlusBook.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Length(min = 3, max = 20)
    @NotEmpty(message = "User name can not be empty")
    private String userName;

    @Length(min = 3, max = 30)
    @NotEmpty(message = "Full name can not be empty")
    private String fullName;
    
    @Length(min = 3, max = 30)
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @Min(16)
    @Max(90)
    private int age;
    
    private String address;
    private String email;
    private Long phoneNum;
    private String avatar;
    private String role;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
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
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    
}

package fit.se2.APlusBook.model;

import fit.se2.APlusBook.dto.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Size(min = 6, max = 60, message = "Username must be between 6 and 60 characters long.")
    private String userName;

//    @Length(min = 3, max = 50)
    private String fullName;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,}$", message = "6 chars min (at least 1 digit & 1 uppercase letter)")
    @Size(min=6, max = 30, message = "Password must be a maximum of 30 characters")
    private String password;
//@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,60}$",
//        message = "6 chars min (at least 1" +
//                "digit & 1 uppercase letter)")
//    private String password;

//    @Min(16)
//    @Max(90)
    private int age;

    private String address;
    private String email;
    private String phoneNum;
    private String avatar;

    private String role;

    public User() {

    }

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

    //Authority information
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singleton(authority);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
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

    public User(@Valid UserDto user, PasswordEncoder encoder) {
        this.userName = user.getUsername();
        this.password = encoder.encode(user.getPassword());
        this.phoneNum = user.getPhoneNum();
        this.email = user.getEmail();
//        this.address = user.getAddress();
    }
}
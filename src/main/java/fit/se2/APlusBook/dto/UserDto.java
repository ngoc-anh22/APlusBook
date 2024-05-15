package fit.se2.APlusBook.dto;

import fit.se2.APlusBook.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class UserDto extends User {

    @Size(min = 6, max = 60, message = "Username must be between 6 and 60 characters long.")
    private String userName;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,}$", message = "6 chars min (at least 1 digit & 1 uppercase letter)")
    @Size(min=6, max = 60, message = "Password must be a maximum of 30 characters")
    private String password;
    private String confirmPassword;
    private String role;
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty(message = "Phone number is required")
    private String phoneNum;
//    @NotEmpty(message = "Address is required")
    private String address;

    public UserDto(String userName, String password, String role, String email, String phoneNum) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public UserDto() {

    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
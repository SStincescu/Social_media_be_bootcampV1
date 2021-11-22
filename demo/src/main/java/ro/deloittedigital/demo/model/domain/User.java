package ro.deloittedigital.demo.model.domain;


import com.sun.istack.NotNull;
import ro.deloittedigital.demo.model.dto.UserDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*@NamedNativeQuery(name ="User.findAllReturnWithoutPassword",
        query = "select username, first_name, last_name, email, gender, date_of_birth From users",
        resultSetMapping ="Mapping.UserDTO")
@SqlResultSetMapping(name = "Mapping.UserDTO",
        classes = @ConstructorResult( targetClass = UserDTO.class,
                columns = {@ColumnResult(name="first_name"),
                        @ColumnResult(name="last_name"),
                        @ColumnResult(name="email"),
                        @ColumnResult(name="gender"),
                        @ColumnResult(name="username"),
                        @ColumnResult(name="date_of_birth")}))
*/
@Entity(name = "users")
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "first_name")
    @NotNull
    private String first_name;

    @Column(name = "last_name")
    @NotNull
    private String last_name;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "gender")
    @NotNull
    private String gender;

    @Column(name = "date_of_birth")
    @NotNull
    private Date date_of_birth;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "role")
    @NotNull
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

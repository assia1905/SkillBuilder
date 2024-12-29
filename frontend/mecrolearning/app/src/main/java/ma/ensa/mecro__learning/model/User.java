package ma.ensa.mecro__learning.model;
import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String password;
    private String name;// Si nécessaire
    private String phone;

    // Constructeur
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(int id,String email, String password,String name,String phone) {
        this.email = email;
        this.password = password;
        this.name = name ;
        this.id = id ;
        this.phone = phone ;
    }

    public User(String name, String email, String phone) {
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

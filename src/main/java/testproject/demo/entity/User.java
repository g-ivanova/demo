package testproject.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
@Column(name="user_fname")
    private String fname;
@Column(name="user_lname")
    private String lname;
@Column(name="user_phone")
    private String phone;

    public User(){}

    public User(int user_id, String user_fname, String user_lname, String user_phone) {
        this.user_id = user_id;
        this.fname = user_fname;
        this.lname = user_lname;
        this.phone = user_phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String user_fname) {
        this.fname = user_fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String user_lname) {
        this.lname = user_lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String user_phone) {
        this.phone = user_phone;
    }
}

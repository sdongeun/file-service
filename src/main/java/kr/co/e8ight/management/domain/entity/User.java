package kr.co.e8ight.management.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "users"
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "user_id",
            nullable = false
    )
    private int id;
    @Column(
            name = "account",
            nullable = false
    )
    private String account;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    public User() {
    }

    public User(int id, String account, String password, String name) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

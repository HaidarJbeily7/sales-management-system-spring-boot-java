package com.example.demo.clients;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String last_name;


    @NotBlank
    @Pattern(regexp = "^[+][1-9]\\d{2}\\d{9}$", message = "mobile is invalid")
    @Column(nullable = false)
    private String mobile;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, nullable = false)
    private LocalDateTime creation_date;

    public Client() {
    }

    public Client(String name, String last_name, String mobile) {
        this.setName(name);
        this.setLast_name(last_name);
        this.setMobile(mobile);
        this.setCreation_date(LocalDateTime.now());
    }

    public void setCreation_date(LocalDateTime dateTime) {
        this.creation_date = dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMobile() {
        return mobile;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", last_name='" + this.getLast_name() + '\'' +
                ", mobile='" + this.getMobile() + '\'' +
                ", creation_date=" + creation_date.toString() +
                '}';
    }
}

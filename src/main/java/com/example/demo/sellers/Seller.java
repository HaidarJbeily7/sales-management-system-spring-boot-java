package com.example.demo.sellers;


import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Nullable
    private String Agency;

    @Column(columnDefinition = "TEXT")
    private String details;

    public Seller() {
    }

    public Seller(Long id, String name, @Nullable String agency, String details) {
        this.id = id;
        this.name = name;
        Agency = agency;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getAgency() {
        return Agency;
    }

    public void setAgency(@Nullable String agency) {
        Agency = agency;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

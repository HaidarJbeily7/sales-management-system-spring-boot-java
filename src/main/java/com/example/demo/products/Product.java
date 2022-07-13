package com.example.demo.products;


import com.example.demo.Category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(insertable = false)
    private LocalDateTime creation_date;

    public Product() {
    }

    public Product(Long id, String name, String description, Category category, LocalDateTime creation_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.creation_date = creation_date;
    }

    public Product(String name, String description, Category category, LocalDateTime creation_date) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.creation_date = creation_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category.getTitle() + '\'' +
                ", creation_date=" + creation_date +
                '}';
    }
}

package com.example.demo.products;


import com.example.demo.categories.Category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(insertable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creation_date;

    public Product() {
    }

    public Product(String name, String description, Category category) {
        this.setName(name);
        this.setDescription(description);
        this.setCategory(category);
        this.setCreation_date();
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

    public void setCreation_date() {
        this.creation_date = LocalDateTime.now().withNano(0);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description == null ? "":description;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreation_date() {
        return creation_date == null ? LocalDateTime.now():creation_date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", category='" + category.getTitle() + '\'' +
                ", creation_date=" + this.getCreation_date().toString() +
                '}';
    }
}

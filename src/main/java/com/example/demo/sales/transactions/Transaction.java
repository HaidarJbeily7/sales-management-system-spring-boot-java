package com.example.demo.sales.transactions;

import com.example.demo.products.Product;
import com.example.demo.sales.Sale;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {

    @EmbeddedId
    TransactionKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("saleId")
    @JoinColumn(name = "sale_id", referencedColumnName = "id", nullable = false)
    private Sale sale;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private int quantity;


    @NotNull
    @Min(value = 0)
    @Column(nullable = false)
    private double unit_price;

    public Transaction() {
    }

    public Transaction(TransactionKey id, Sale sale, Product product, int quantity, double unit_price) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }


    public void setId(TransactionKey id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

}

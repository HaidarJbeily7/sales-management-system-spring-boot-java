package com.example.demo.sales;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.sellers.Seller;
import com.example.demo.clients.Client;


@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double total;


    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;


    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
    private Seller seller;

    @Column(insertable = false)
    private LocalDateTime creation_date;

    @NotNull
    @OneToMany(mappedBy = "sale")
    List<Transaction> sale_transactions;

    public Sale() {
    }

    public Sale(double total, Client client, Seller seller) {
        this.setId(id);
        this.setTotal(total);
        this.setClient(client);
        this.setSeller(seller);
        this.setCreation_date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Transaction> getSale_transactions() {
        return sale_transactions;
    }

    public void setSale_transactions(List<Transaction> sale_transactions) {
        this.sale_transactions = sale_transactions;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date() {
        this.creation_date = LocalDateTime.now();
    }
}

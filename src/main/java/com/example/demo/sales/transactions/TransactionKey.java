package com.example.demo.sales.transactions;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TransactionKey implements Serializable {

    @Column(name = "product_id")
    Long productId;

    @Column(name = "sale_id")
    Long saleId;

    public TransactionKey() {
    }

    public TransactionKey(Long productId, Long saleId) {
        this.productId = productId;
        this.saleId = saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TransactionKey that = (TransactionKey) obj;

        if (!saleId.equals(that.saleId)) return false;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        int result = saleId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }
}

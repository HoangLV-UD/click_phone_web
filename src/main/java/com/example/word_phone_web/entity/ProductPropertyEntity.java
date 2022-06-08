package com.example.word_phone_web.entity;

import javax.persistence.*;

@Entity
@Table(name = "property_product", schema = "world_phone", catalog = "")
public class ProductPropertyEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "PRICE")
    private long price;

    @Basic
    @Column(name = "STATUS")
    private String status;


    @Basic
    @Column(name = "QUANTITY")
    private long quantity;

    @Basic
    @Column(name = "ROM_ID")
    private Long romId;

    @Basic
    @Column(name = "COLOR_ID")
    private Long colorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Long getRomId() {
        return romId;
    }

    public void setRomId(Long romId) {
        this.romId = romId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }
}

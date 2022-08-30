package com.example.word_phone_web.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 10/08/2022
 * Project_name: com.example.word_phone_web.entity
 */

@Entity
@Table(name = "orders", schema = "world_phone", catalog = "")
public class OrdersEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private CustomerEntity customerEntity;

    @Basic
    @Column(name = "STAFF_ID")
    private Long  staffEntity;

    @Basic
    @Column(name = "NOTE")
    private String note;

    @Basic
    @Column(name = "TOTAL_MONEY")
    private Long totalMoney;

    @ManyToOne
    @JoinColumn(name = "VOUCHER_ID", referencedColumnName = "ID")
    private VoucherEntity voucherEntity;

    @ManyToOne
    @JoinColumn(name = "PROMOTION_ID", referencedColumnName = "ID")
    private PromotionEnity promotionEnity;

    @Basic
    @Column(name = "ADDRESS")
    private String address;

    @Basic
    @Column(name = "CODE_ORDER")
    private String codeOrder;

    @Basic
    @Column(name = "STATUS")
    private String status;


    @Basic
    @Column(name = "STATUS_PAY")
    private Integer statusPay;

    @OneToMany(mappedBy = "ordersEntity")
    private List<OrdersDetailEntity> ordersDetailEntities;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public Long getStaffEntity() {
        return staffEntity;
    }

    public void setStaffEntity(Long staffEntity) {
        this.staffEntity = staffEntity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public VoucherEntity getVoucherEntity() {
        return voucherEntity;
    }

    public void setVoucherEntity(VoucherEntity voucherEntity) {
        this.voucherEntity = voucherEntity;
    }

    public PromotionEnity getPromotionEnity() {
        return promotionEnity;
    }

    public void setPromotionEnity(PromotionEnity promotionEnity) {
        this.promotionEnity = promotionEnity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrdersDetailEntity> getOrdersDetailEntities() {
        return ordersDetailEntities;
    }

    public void setOrdersDetailEntities(List<OrdersDetailEntity> ordersDetailEntities) {
        this.ordersDetailEntities = ordersDetailEntities;
    }

    public Integer getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(Integer statusPay) {
        this.statusPay = statusPay;
    }
}

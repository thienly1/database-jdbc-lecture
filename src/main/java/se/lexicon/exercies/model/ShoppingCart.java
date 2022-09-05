package se.lexicon.exercies.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class ShoppingCart {
    private int id;
    private LocalDateTime lastUpdate;
    private String orderStatus;
    private String deliveryAddress;
    private String customerReference;
    private boolean paymentApproved;

    public ShoppingCart(int id, LocalDateTime lastUpdate, String orderStatus, String deliveryAddress, String customerReference, boolean paymentApproved) {
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.customerReference = customerReference;
        this.paymentApproved = paymentApproved;
    }

    public ShoppingCart() {
    }

    public ShoppingCart(int id, LocalDateTime lastUpdate, String orderStatus, String deliveryAddress, String customerReference) {
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.customerReference = customerReference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public boolean isPaymentApproved() {
        return paymentApproved;
    }

    public void setPaymentApproved(boolean paymentApproved) {
        this.paymentApproved = paymentApproved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCart)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return getId() == that.getId() && isPaymentApproved() == that.isPaymentApproved() && Objects.equals(getLastUpdate(), that.getLastUpdate()) && Objects.equals(getOrderStatus(), that.getOrderStatus()) && Objects.equals(getDeliveryAddress(), that.getDeliveryAddress()) && Objects.equals(getCustomerReference(), that.getCustomerReference());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastUpdate(), getOrderStatus(), getDeliveryAddress(), getCustomerReference(), isPaymentApproved());
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", lastUpdate=" + lastUpdate +
                ", orderStatus='" + orderStatus + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", customerReference='" + customerReference + '\'' +
                ", paymentApproved=" + paymentApproved +
                '}';
    }
}


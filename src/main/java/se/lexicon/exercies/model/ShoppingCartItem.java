package se.lexicon.exercies.model;

import java.util.Objects;

public class ShoppingCartItem {
    private int id;
    private int amount;
    private double totalPrice;
    private Product item;
    private ShoppingCart shoppingCart;

    public ShoppingCartItem(int id, int amount, double totalPrice, Product item, ShoppingCart shoppingCart) {
        this.id = id;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.item = item;
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int amount, double totalPrice, Product item, ShoppingCart shoppingCart) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.item = item;
        this.shoppingCart = shoppingCart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartItem)) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return getId() == that.getId() && getAmount() == that.getAmount() && Double.compare(that.getTotalPrice(), getTotalPrice()) == 0 && Objects.equals(getItem(), that.getItem()) && Objects.equals(getShoppingCart(), that.getShoppingCart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount(), getTotalPrice(), getItem(), getShoppingCart());
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "id=" + id +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", item=" + item +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}

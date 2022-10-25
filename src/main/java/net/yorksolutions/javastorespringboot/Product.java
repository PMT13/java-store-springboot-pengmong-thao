package net.yorksolutions.javastorespringboot;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Product {
    private long id;

    @JsonProperty
    private String product_name;

    @JsonProperty
    private String display_name;

    @JsonProperty
    private double price;

    @JsonProperty
    private double salePercent;

    @JsonProperty
    private boolean onSale;
    private double currentPrice;

    public Product(){}

    public Product(String product_name, String display_name, double price, double salePercent, boolean onSale) {
        this.id = new Date().getTime();
        this.product_name = product_name.toLowerCase();
        this.display_name = display_name;
        this.price = price;
        this.salePercent = salePercent;
        this.onSale = onSale;
        this.setCurrentPrice();
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getSalePercent() {
        return salePercent;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name.toLowerCase();
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSalePercent(double salePercent) {
        this.salePercent = salePercent;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
        this.setCurrentPrice();
    }

    public void setCurrentPrice(){
        if(this.isOnSale()){
            this.currentPrice = (this.getPrice() - (this.getPrice() * (this.getSalePercent()/100)));
        }else{
            this.currentPrice = this.getPrice();
        }
    }
}

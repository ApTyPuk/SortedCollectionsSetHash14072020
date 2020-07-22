package com.Artur;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock;
    private int reservedQuantity = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setPrice(double price) {
        if(price >0.00){
            this.price = price;
        }
    }

    public int reserve(int quantity){
        int i = this.reservedQuantity + quantity;
        if (i > this.quantityStock){
            return 0;
        }else{
            this.reservedQuantity = i;
            return i;
        }
    }

    public int unreserveItem(int quantity){
        int i = this.reservedQuantity - quantity;
        if((quantity > 0) && (i >=0)){
            this.reservedQuantity = i;
            return i;
        }
        return 0;
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if(newQuantity >= 0){
            this.quantityStock = newQuantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if(obj == this){
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
//        System.out.println("Entering StockItem.compareTo");
        if(this ==  o){
            return 0;
        }

        if(o!= null){
            return this.name.compareTo(o.getName()); //using in build functionality for String;
        }

        throw new NullPointerException();   //Have to assume we are not comparing something that is 'null'.
    }

    @Override
    public String toString() {
        return "'"+this.name + "' price " + this.price;
    }







}

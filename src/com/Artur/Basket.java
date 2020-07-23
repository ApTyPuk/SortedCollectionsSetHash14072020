package com.Artur;

import java.util.*;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public int addToBasket(StockItem item, int quantity){
        if((item != null) && (quantity >0)){
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public int unreserve(StockItem item, int quantity){
        if((item != null) && (quantity>0)){
            int inBasket = list.getOrDefault(item, 0);
            int delta = inBasket - quantity;
            if((inBasket != 0) && (delta > 0)){
                list.put(item, delta);
                item.unreserveItem(quantity);
                return quantity;
            }else if ((inBasket != 0) && (delta == 0)){
                list.remove(item);
                item.unreserveItem(quantity);
                return quantity;
            }
        }
        return 0;
    }

    public Map<StockItem,Integer> items(){
        return Collections.unmodifiableMap(list);
    }

    public void checkOut(){
        System.out.println("->The basket "+ this.name+" is checked out.");
        list.clear();
    }


    @Override
    public String toString() {
        String s = "Shopping basket " + name + " contains " + list.size() +
                ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost= 0.0;
        for(Map.Entry<StockItem, Integer> item : list.entrySet()){
            s = s +"\t"+ item.getKey() + ". " +item.getValue() + " in basket\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}

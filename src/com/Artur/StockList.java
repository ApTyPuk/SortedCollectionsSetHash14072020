package com.Artur;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;


    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item){
        if(item != null){
            //check if we already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // If there are already stocks on this item, adjust the quantity.
            if(inStock!=item){
                //parsing the quantity we currently had in a map to new item.
                item.adjustStock(inStock.quantityInStock());
            }
            //if it doesn't exist it will add new item. If it exists it will overwrite the quantity.
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0; //Returning 0 to indicate that there was now stock movement.
    }

    public int sellStock(String item, int quantity){
        //Using just 'item' because it is already a string. And 'defaultedValue' to 'null' if it doesn't already
        //exists because we are selling stock. The assumption is that the stock item has to exist.
        StockItem inStock = list.getOrDefault(item, null);

        if((inStock!=null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)){
            inStock.adjustStock(-quantity);
            return quantity; //to indicate how many items we are getting out of stock.
        }
        return 0; //To indicate that nothing was taken out the stock.
    }

    public StockItem get(String key){
        return list.get(key);
    }

    public Map<String, Double> priceList(){
        Map<String, Double> prices = new LinkedHashMap<>();
        for(Map.Entry<String, StockItem> item : list.entrySet()){
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    //Returning the items which are in stock.
    public Map<String, StockItem> items(){
        //wrapper around the list. Provides read-only access to internal Map.
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()){
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + " . There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + totalCost;



    }
}

package com.Artur;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList.toString());

//        for(String s : stockList.items().keySet()){
//            System.out.println(s);
//        }

        Basket timsBasket = new Basket("Tim");
        reserveItem(timsBasket, "car", 1);
        reserveItem(timsBasket, "spanner", 5);
        reserveItem(timsBasket, "juice", 17);
//        reserveItem(timsBasket, "juice", 1);
        reserveItem(timsBasket, "cup", 10);
        reserveItem(timsBasket, "bread", 10);
//        System.out.println(timsBasket);

        Basket artursBasket = new Basket("Artur");
        reserveItem(artursBasket, "car", 1);
        reserveItem(artursBasket, "spanner", 5);
        reserveItem(artursBasket, "juice", 17);
//        reserveItem(artursBasket, "juice", 1);
        reserveItem(artursBasket, "cup", 10);
        reserveItem(artursBasket, "bread", 10);
        unreserveItem(artursBasket, "bread", 10);
        unreserveItem(artursBasket, "towel", 1);
        System.out.println(timsBasket);
        System.out.println(artursBasket);



//        System.out.println(stockList.toString());

//        for(Map.Entry<String, Double> price : stockList.priceList().entrySet()){
//            System.out.println(price.getKey() + " costs "+ price.getValue());
//        }

        for (Map.Entry<String, StockItem> item : stockList.items().entrySet()){
            if(item.getValue().getReservedQuantity() != 0){
                StockItem temp1 = item.getValue();
                System.out.println("\t"+temp1.getName() + ": reserved-> " + temp1.getReservedQuantity()+
                        ". In stock-> " + temp1.quantityInStock());
            }
        }

        checkOut(timsBasket);
        checkOut(artursBasket);

        System.out.println(stockList.toString());
        System.out.println(timsBasket);
        System.out.println(artursBasket);


    }

    public static void unreserveItem(Basket basket, String item, int quantity){
        StockItem item1 = stockList.get(item);
        if(basket.items().containsKey(item1)){
            System.out.println("Unreserving "+item+" with -"+quantity);
            basket.unreserve(item1, quantity);
        }else {
            System.out.println("Unable to unreserve " + item);
        }
    }

    public static void checkOut(Basket basket){
        System.out.println("\n->Checking out:");
        System.out.println(basket.toString());
        for(Map.Entry<StockItem, Integer> basketItem : basket.items().entrySet()){
            int basketQTY = basketItem.getValue();
//            System.out.println("Checking out " + basketItem.getValue() + " "+ basketQTY +
//                    (basketQTY == 1 ? " pc." : " pcs."));
            basketItem.getKey().adjustStock(-basketQTY);
        }
        basket.checkOut();
    }


    public static int reserveItem(Basket basket, String item, int quantity){
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null){
            System.out.println("We don't sell "+ item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0){
            basket.addToBasket(stockItem,  quantity);
            return quantity;
        }
        System.out.println("Unable to reserve: '"+item+"' with quantity "+ quantity + (quantity == 1 ? " pc." : " pcs."));
        return 0; //if get here means have no sufficient stock to sell.
    }



}

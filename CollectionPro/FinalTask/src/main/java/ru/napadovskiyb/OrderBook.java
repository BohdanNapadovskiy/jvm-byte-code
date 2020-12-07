package ru.napadovskiyb;


import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Package of CollectionPro finalTask.
 * Main class for order book.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 09.08.2017
 */
public class OrderBook {

    /**
     * Unsorted map for all orders.
     */
    private HashMap<Integer, Order> unsortedMap = new HashMap<Integer, Order>();

    /**
     * TreeMap for buy orders.
     */
    private TreeMap<Double, HashMap<Integer, Order>> buyTree = new TreeMap<>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    });

    /***
     *TreeMap for sell orders.
     */
    private TreeMap<Double, HashMap<Integer, Order>> sellTree = new TreeMap<>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return -o1.compareTo(o2);
        }
    });

    /**
     *Method add order too maps.
     * @param order order for add.
     */
    public void addOrder(Order order) {
        this.unsortedMap.put(order.getIdOrder(), order);

        if (order.isTypeOperation()) {
            addToBuySortMap(order);
        } else {
            addToSellMap(order);
        }
    }

    /**
     * Method for add orders too buy map.
     * @param order for add.
     */
    private void addToBuySortMap(Order order) {

        if (this.buyTree.containsKey(order.getPrice())) {
            this.buyTree.get(order.getPrice()).put(order.getIdOrder(), order);
        } else {
            HashMap<Integer, Order> tmpMap = new HashMap<>();
            tmpMap.put(order.getIdOrder(), order);
            this.buyTree.put(order.getPrice(), tmpMap);
        }
    }

    /**
     * Method add order to sellMap.
     * @param order too add.
     */
    private void addToSellMap(Order order) {

        if (this.sellTree.containsKey(order.getPrice())) {
            this.sellTree.get(order.getPrice()).put(order.getIdOrder(), order);
        } else {
            HashMap<Integer, Order> tmpMap = new HashMap<>();
            tmpMap.put(order.getIdOrder(), order);
            this.sellTree.put(order.getPrice(), tmpMap);
        }
    }

    /**
     * Method delete order from all maps.
     * @param id id order for delete
     */
    public void deleteOrder(Integer id) {
        Order deleteOrder = unsortedMap.get(id);
        if (this.unsortedMap.containsKey(id)) {
            this.unsortedMap.remove(id);
            if (this.buyTree.containsKey(deleteOrder.getPrice())) {
                this.buyTree.get(deleteOrder.getPrice()).remove(deleteOrder.getIdOrder());
                if (this.buyTree.get(deleteOrder.getPrice()).size() == 0) {
                    this.buyTree.remove(deleteOrder.getPrice());
                }
            }
            if (this.sellTree.containsKey(deleteOrder.getPrice())) {
                this.sellTree.get(deleteOrder.getPrice()).remove(deleteOrder.getIdOrder());
                if (this.sellTree.get(deleteOrder.getPrice()).size() == 0) {
                    this.sellTree.remove(deleteOrder.getPrice());
                }

            }
        }
    }

    /**
     * Method calculate sum all element in tree.
     * @param mapForCheck map for calculate.
     * @return result.
     */
    private int takeSumAllElement(HashMap<Integer, Order> mapForCheck) {
        int result = 0;
        for (Order tmpOrder:mapForCheck.values()) {
            result = result + tmpOrder.getVolume();
        }
        return result;
    }

    /**
     * Method check value with value in map.
     * @param editMap map for edit
     * @param value value for check.
     */
    private void checkVolume(HashMap<Integer, Order> editMap, int value) {
        Iterator<Order> iterMap = editMap.values().iterator();
        while (iterMap.hasNext()) {
            Order nextOrder = iterMap.next();
            if (value <= nextOrder.getVolume()) {
                nextOrder.setVolume(nextOrder.getVolume() - value);
                break;
            } else {
                iterMap.remove();
            }
        }

    }


    /**
     * Method offset orders in book.
     */
    public void offsettingOrders() {
        Iterator<Double> buyKeys = this.buyTree.keySet().iterator();
        Iterator<Double> sellKeys = this.sellTree.keySet().iterator();
        if (buyKeys.hasNext() && sellKeys.hasNext()) {
            Double buyPrice = buyKeys.next();
            Double sellPrice = sellKeys.next();
            while (buyKeys.hasNext() && sellKeys.hasNext()) {
                HashMap buyElements = this.buyTree.get(buyPrice);
                HashMap sellElements = this.sellTree.get(sellPrice);
                int sumAllBuyElement = takeSumAllElement(buyElements);
                int sumAllSellElement = takeSumAllElement(sellElements);
                if ((sellPrice >= buyPrice) && (sumAllBuyElement > sumAllSellElement)) {
                    checkVolume(buyElements, sumAllSellElement);
                    sellKeys.remove();
                    sellPrice = sellKeys.next();
                } else if ((sellPrice >= buyPrice) && (sumAllBuyElement < sumAllSellElement)) {
                    checkVolume(sellElements, sumAllBuyElement);
                    buyKeys.remove();
                    buyPrice = buyKeys.next();
                } else if ((sellPrice >= buyPrice) && (sumAllBuyElement == sumAllSellElement)) {
                    sellKeys.remove();
                    sellPrice = sellKeys.next();
                    buyKeys.remove();
                    buyPrice = buyKeys.next();
                } else {
                    break;
                }

            }
        }

    }

    /**
     * Method return main map.
     * @return map;
     */
    public HashMap<Integer, Order> getUnsortedMap() {
        return this.unsortedMap;
    }

    /**
     * Method calculate volume in map.
     * @param map map.
     * @return result.
     */
    private int calculateValueMap(HashMap<Integer, Order> map) {
        int result = 0;
        for (Order order : map.values()) {
            result += order.getVolume();
        }
        return result;
    }


    /**
     * Method print map.
     */
    public void printMap() {
        Iterator<Double> buyMap = this.buyTree.keySet().iterator();
        Iterator<Double> sellMap = this.sellTree.keySet().iterator();
        while (buyMap.hasNext() && sellMap.hasNext()) {
            Double nextBuy = buyMap.next();
            Double nextSell = sellMap.next();
            HashMap<Integer, Order> mapBuy = this.buyTree.get(nextBuy);
            HashMap<Integer, Order> mapSell = this.sellTree.get(nextSell);
            System.out.println(String.format("%s@%s - %s@%s", calculateValueMap(mapBuy), nextBuy, calculateValueMap(mapSell), nextSell));
        }
    }

}

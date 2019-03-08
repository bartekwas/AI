package lab_1.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Individual {

    private Node[] nodesOrder;
    private ArrayList<Item> items;
    private double totalDistance;

    public double getFinalRatio() {
        return finalRatio;
    }

    public void setFinalRatio(double finalRatio) {
        this.finalRatio = finalRatio;
    }

    private double finalRatio;

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    private double totalTime;

    public Individual(Node [] nodesOrder){
        this.nodesOrder = nodesOrder;
    }

    public Node[] getNodesOrder() {
        return nodesOrder;
    }

    public void setNodesOrder(Node[] nodesOrder) {
        this.nodesOrder = nodesOrder;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getTotalProfit(){
        if(items == null || items.isEmpty()){
            return 0;
        } else {
            return items.stream().mapToDouble(i -> i.getProfit()).sum();
        }
    }

    public double getTotalWeight(){
        if (items == null ||items.isEmpty()){
            return 0;
        } else {
            return items.stream().mapToDouble(i -> i.getWeight()).sum();
        }
    }

    public void addItemBasedOnProfitWeightRation(Item item, double maxCapacity, boolean logging){
        if(logging) {
            System.out.println("");
            System.out.println("Current indyvidual's backpack weight: " + getTotalWeight());
            System.out.println("Current indyvidual profitWeightRation: " + getTotalProfit() / getTotalWeight());
            System.out.println("New item profitWeightRation: " + item.getProfitWeightRation());
            System.out.println("New item Weight: " + item.getWeight());
        }
        if (item.getWeight() <= maxCapacity) {
            if (getTotalWeight() + item.getWeight() <= maxCapacity) {
                if (items == null) {
                    items = new ArrayList<Item>();
                    items.add(item);
                    if (logging) {
                        System.out.println("created new list and added item to empty list");
                        System.out.println("");
                    }
                } else {
                    if (items.isEmpty()) {
                        items.add(item);
                        if (logging) {
                            System.out.println("added item to empty list");
                            System.out.println("");
                        }
                    } else {
                        for (int i = 0; i < items.size(); i++) {
                            if(logging) {
                                System.out.println("current list ration: " + items.get(i).getProfitWeightRation());
                            }
                            if (item.getProfitWeightRation() > items.get(i).getProfitWeightRation()) {
                                items.add(i, item);
                                if (logging) {
                                    System.out.println("added item to list " + item.getProfitWeightRation());
                                    System.out.println("");
                                }
                                break;
                            }
                        }
                    }
                }
            } else {
                if (logging) {
                    System.out.println("current capacity" + getTotalWeight());
                    System.out.println("removing worst one");
                    System.out.println("");
                }
                items.remove(items.size() - 1);
                addItemBasedOnProfitWeightRation(item, maxCapacity, logging);
            }
        }
    }


    public void addItemBasedOnGreedyAlgorithm(Item item, double maxCapacity, boolean logging){
        if(logging) {
            System.out.println("");
            System.out.println("Current indyvidual's backpack weight: " + getTotalWeight());
            System.out.println("New item Weight: " + item.getWeight());
        }
        if(item.getWeight() < maxCapacity) {
            if(item.getWeight() + getTotalWeight() < maxCapacity) {
                if (items == null) {
                    items = new ArrayList<Item>();
                    items.add(item);
                    if(logging) {
                        System.out.println("created new items list and added item");
                    }
                } else {
                    items.add(item);
                    if(logging) {
                        System.out.println("added item");
                    }
                }
            }
        }
    }


}

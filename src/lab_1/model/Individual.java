package lab_1.model;

import lab_1.utils.LogWriter;

import java.util.ArrayList;

public class Individual {

    private Node[] nodesOrder;
    private ArrayList<Item> items;
    private double totalDistance;
    private double finalRatio;
    private double totalTime;

    public double getFinalRatio() {
        return finalRatio;
    }

    public void setFinalRatio(double finalRatio) {
        this.finalRatio = finalRatio;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

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

    public void addItemBasedOnProfitWeightRation(Item item, double maxCapacity, LogWriter logWriter){
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("Current indyvidual's backpack weight: " + getTotalWeight());
        sb.append("\n");
        sb.append("Current indyvidual profitWeightRation: " + getTotalProfit() / getTotalWeight());
        sb.append("\n");
        sb.append("New item profitWeightRation: " + item.getProfitWeightRation());
        sb.append("\n");
        sb.append("New item Weight: " + item.getWeight());

        if (item.getWeight() <= maxCapacity) {
            if (getTotalWeight() + item.getWeight() <= maxCapacity) {
                if (items == null) {
                    items = new ArrayList<Item>();
                    items.add(item);

                    sb.append("created new list and added item to empty list");
                    sb.append("\n");

                } else {
                    if (items.isEmpty()) {
                        items.add(item);

                        sb.append("added item to empty list");
                        sb.append("\n");

                    } else {
                        for (int i = 0; i < items.size(); i++) {

                            sb.append("current list ration: " + items.get(i).getProfitWeightRation());
                            sb.append("\n");

                            if (item.getProfitWeightRation() > items.get(i).getProfitWeightRation()) {
                                items.add(i, item);
                                sb.append("added item to list " + item.getProfitWeightRation());
                                sb.append("\n");
                                break;
                            }
                        }
                    }
                }
            } else {

                sb.append("current capacity" + getTotalWeight());
                sb.append("removing worst one");
                sb.append("\n");
                items.remove(items.size() - 1);
                addItemBasedOnProfitWeightRation(item, maxCapacity, logWriter);
            }
        }
        logWriter.writeToLog(sb.toString());
    }


    public void addItemBasedOnGreedyAlgorithm(Item item, double maxCapacity, LogWriter logWriter){
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("Current indyvidual's backpack weight: " + getTotalWeight());
        sb.append("New item Weight: " + item.getWeight());

        if(item.getWeight() < maxCapacity) {
            if(item.getWeight() + getTotalWeight() < maxCapacity) {
                if (items == null) {
                    items = new ArrayList<Item>();
                    items.add(item);

                    sb.append("created new items list and added item");
                    sb.append("\n");

                } else {
                    items.add(item);
                    sb.append("added item");
                    sb.append("\n");
                }
            }
        }
        logWriter.writeToLog(sb.toString());
    }


}

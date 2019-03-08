package lab_1.model;

public class Item {
    String id;
    double profit;
    double weight;
    String nodeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public double getProfitWeightRation(){
        return this.profit / this.weight;
    }

    @Override
    public String toString() {
        return "id: " + id + " (" + "p: "+profit + ", " + "w: " +weight + ")";
    }
}

package lab_1.model;

public class Individual {

    private Node[] nodesOrder;
    private double totalDistance;

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






}

package lab_1.model;


import java.math.BigDecimal;
import java.util.List;



public class Configuration {

    String problemName;
    String dataType;
    int dimension;
    int numberOfItems;
    BigDecimal capacity;
    BigDecimal minSpeed;
    BigDecimal maxSpeed;
    BigDecimal rentingRatio;
    String edgeWeightType;

    List<Node> nodes;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(BigDecimal minSpeed) {
        this.minSpeed = minSpeed;
    }

    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getRentingRatio() {
        return rentingRatio;
    }

    public void setRentingRatio(BigDecimal rentingRatio) {
        this.rentingRatio = rentingRatio;
    }

    public String getEdgeWeightType() {
        return edgeWeightType;
    }

    public void setEdgeWeightType(String edgeWeightType) {
        this.edgeWeightType = edgeWeightType;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration{" +
                "\n problemName: '" + problemName + '\'' +
                ", \n dataType: '" + dataType + '\'' +
                ", \n dimension: " + dimension +
                ", \n numberOfItems: " + numberOfItems +
                ", \n capacity: " + capacity +
                ", \n minSpeed: " + minSpeed +
                ", \n maxSpeed: " + maxSpeed +
                ", \n rentingRatio: " + rentingRatio +
                ", \n edgeWeightType: '" + edgeWeightType + '\'' +
                ", \n nodes: ");
        for (Node node : nodes) {
            sb.append("\n  ");
            sb.append(node);
        }
        return sb.toString();
    }
}
